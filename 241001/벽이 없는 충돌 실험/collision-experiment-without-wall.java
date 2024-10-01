import java.util.*;
import java.io.*;
public class Main {
 
    static class wNum {
        int w, num;

        public wNum(int w, int num) {
            this.w = w;
            this.num = num;
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
    static HashMap<Integer, HashMap<Integer, wNum>> memo; // 최고 값 갱신할 것
    static Deque<Bid> bids;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
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
        memo = new HashMap<>();
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
                d = 1;
            } else if (D == 'L') {
                d = 2;
            } else if (D == 'R') {
                d = 3;
            }
            if (d != -1) {
                if (!memo.containsKey(r)) {
                    memo.put(r, new HashMap<>());
                }
                memo.get(r).put(c, new wNum(w, i));
                bids.add(new Bid(r, c, w, d, i));
            }
        }
    }

    public static void move() {
        int t = 0;
        boolean hasCollision = false;  // 충돌 발생 여부 체크
        while (t < 5000 && !bids.isEmpty()) {
            t++;
            Deque<Bid> nextBids = new LinkedList<>();
            Map<String, List<Bid>> collisionMap = new HashMap<>();  // 충돌 위치 저장
            Map<String, Integer> visitTimeMap = new HashMap<>();    // 좌표 도달 시간 저장

            while (!bids.isEmpty()) {
                Bid now = bids.poll();
                int r = now.r;
                int c = now.c;
                int w = now.w;
                int d = now.d;
                int num = now.num;
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];

                String key = nr + "," + nc;

                // 도착한 좌표에 대해 시간 체크
                if (visitTimeMap.containsKey(key)) {
                    // 교차점에서 충돌이 발생하는 경우
                    int previousTime = visitTimeMap.get(key);
                    if (previousTime == t - 1) { // 이전 턴에 도달한 다른 구슬과의 교차 충돌
                        answer = t * 2 - 1;  // 교차점 충돌, 정확히 중간에서 발생
                        hasCollision = true;
                    }
                }

                visitTimeMap.put(key, t); // 좌표에 도달한 시간을 기록

                // 충돌 목록에 저장
                if (!collisionMap.containsKey(key)) {
                    collisionMap.put(key, new ArrayList<>());
                }
                collisionMap.get(key).add(new Bid(nr, nc, w, d, num));
            }

            // 충돌 처리
            for (String key : collisionMap.keySet()) {
                List<Bid> bidList = collisionMap.get(key);
                if (bidList.size() > 1) {  // 충돌 발생
                    hasCollision = true;
                    answer = t * 2;
                    // 가장 영향력이 큰 구슬을 남기고 제거
                    Bid maxBid = Collections.max(bidList);  // Comparable에 따라 영향력이 큰 구슬 선택
                    nextBids.add(maxBid);
                } else {
                    nextBids.add(bidList.get(0));  // 충돌이 없으면 그대로 추가
                }
            }

            bids = nextBids;  // 다음 턴으로 이동
        }

        if (!hasCollision) {
            answer = -1;  // 충돌이 없을 경우
        }
    }
}