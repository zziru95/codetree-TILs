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
    static Map<Integer, TreeSet<Bid>> bidMap;  // 좌표별 입찰자 관리
    static Deque<Bid> bids;  // 움직일 입찰자 목록
    static Set<String> collisionPoints;  // 충돌 좌표 기록
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
        bidMap = new HashMap<>();
        collisionPoints = new HashSet<>();
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
            bids.add(new Bid(r, c, w, d, i));
        }
    }

    public static void move() {
        for (int t = 1; t < 3000; t++) {
            Map<Integer, TreeSet<Bid>> nextBidMap = new HashMap<>();
            
            while (!bids.isEmpty()) {
                Bid now = bids.poll();
                int nr = now.r + direction[now.d][0];
                int nc = now.c + direction[now.d][1];

                // 충돌 좌표 체크
                String key = nr + "," + nc;
                if (collisionPoints.contains(key)) {
                    answer = t * 2;
                    continue;
                }

                // 다음 좌표에 입찰자가 있을 때 충돌 발생
                TreeSet<Bid> futureBids = nextBidMap.getOrDefault(nr, new TreeSet<>());
                if (!futureBids.isEmpty()) {
                    collisionPoints.add(key);  // 충돌 좌표 추가
                    answer = t * 2;
                } else {
                    // 입찰자를 좌표에 추가
                    futureBids.add(new Bid(nr, nc, now.w, now.d, now.num));
                    nextBidMap.put(nr, futureBids);
                }
            }

            bidMap = nextBidMap;
            if (bids.isEmpty()) break;
        }
    }
}