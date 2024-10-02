import java.util.*;
import java.io.*;
public class Main {

    static class wNum {
        int w, num, d;

        public wNum(int w, int num, int d) {
            this.w = w;
            this.num = num;
            this.d = d;
        }
    }

    static class Bid implements Comparable<Bid> {
        int r;
        int c;
        int w;
        int d;
        int num;

        public Bid(int r, int c, int w, int d, int num) {

            this.r = r;
            this.c = c;
            this.w = w;
            this.d = d;
            this.num = num;
        }

        @Override
        public int compareTo(Bid o) {
            if (this.w == o.w) {
                return o.num - this.num;
            }
            return o.w - this.w;
        }
    }

    static int T, N;
    static Map<Integer, TreeMap<Integer, wNum>> memo; //최고 값 갱신할 것
    static Deque<Bid> bids;
    static int[][] direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // U, L, D, R
    static int answer;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            init(br);
            move();
            System.out.println(answer);
        }


    }

    public static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        answer = -1;
        memo = new TreeMap<>();
        bids = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char D = st.nextToken().charAt(0);
            int d = -1;
            if (D == 'U') {
                d = 0;
            } else if (D == 'D') {
                d = 2;
            } else if (D == 'L') {
                d = 1;
            } else if (D == 'R') {
                d = 3;
            }
            if (d != -1) {
                if (!memo.containsKey(r)) {
                    memo.put(r, new TreeMap<>());
                }
                memo.get(r).put(c, new wNum(w, i, d));
                bids.add(new Bid(r, c, w, d, i));
            }
        }
    }


    public static void move() {
        for (int t = 1; t < 4000; t++) {
            Deque<Bid> nextBids = new LinkedList<>();
            TreeMap<Integer, TreeMap<Integer, wNum>> nextMemo = new TreeMap<>();

            while (!bids.isEmpty()) {
                Bid now = bids.poll();
                int r = now.r;
                int c = now.c;
                int w = now.w;
                int d = now.d;
                int num = now.num;
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];

                // 내자리로 오는 친구가 있을 때 방향따져서 반대방향이면 컷
                wNum existing = nextMemo.getOrDefault(r, new TreeMap<>()).get(c);
                if (existing != null && (existing.d + 2) % 4 == d) {
                    answer = t * 2 - 1;
                    if (w > existing.w || (w == existing.w && num > existing.num)) {
                        removeBid(nextBids, r, c);
                        nextMemo.get(r).remove(c);
                    } else {
                        continue;
                    }
                }

                // 다음 내가 가려는 곳에 또 오는 친구가 있을 때
                wNum future = nextMemo.getOrDefault(nr, new TreeMap<>()).get(nc);
                if (future != null) {
                    answer = t * 2;
                    if (w > future.w || (w == future.w && num > future.num)) {
                        removeBid(nextBids, nr, nc);
                        nextBids.add(new Bid(nr, nc, w, d, num));
                        nextMemo.get(nr).put(nc, new wNum(w, num, d));
                    }
                } else {
                    nextMemo.computeIfAbsent(nr, k -> new TreeMap<>()).put(nc, new wNum(w, num, d));
                    nextBids.add(new Bid(nr, nc, w, d, num));
                }
            }

            memo = nextMemo;
            bids = nextBids;
            if (bids.isEmpty()) break;
        }
    }

    public static void removeBid(Deque<Bid> deque, int r, int c) {
        Iterator<Bid> iterator = deque.iterator();
        while (iterator.hasNext()) {
            Bid current = iterator.next();
            if (current.r == r && current.c == c) {
                iterator.remove();
                break;
            }
        }
    }
}