import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int to;
        long fare;
        int time;

        public Edge(int to, long fare, int time) {
            this.to = to;
            this.fare = fare;
            this.time = time;
        }
    }

    static int A, B, N;
    static final long INF = Long.MAX_VALUE;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        graph = new ArrayList[1001];
        for (int i = 0; i <= 1000; i++) {
            graph[i] = new ArrayList<>();
        }

        // 각 버스의 노선 입력 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long fare = Long.parseLong(st.nextToken());
            int stopCount = Integer.parseInt(st.nextToken());

            int[] route = new int[stopCount];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < stopCount; j++) {
                route[j] = Integer.parseInt(st.nextToken());
            }

            // 노선 연결 정보 추가 (인접 리스트에 저장)
            for (int j = 0; j < stopCount - 1; j++) {
                for (int k = j + 1; k < stopCount; k++) {
                    int from = route[j];
                    int to = route[k];
                    int travelTime = k - j;

                    graph[from].add(new Edge(to, fare, travelTime));
                }
            }
        }

        // 다익스트라 알고리즘 실행
        long[] result = dijkstra(A, B);
        System.out.println(result[0] + " " + result[1]);
    }

    // 다익스트라 알고리즘 구현
    public static long[] dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> {
            if (a.fare == b.fare) return Integer.compare(a.time, b.time);
            return Long.compare(a.fare, b.fare);
        });

        long[] minFare = new long[1001];
        long[] minTime = new long[1001];
        Arrays.fill(minFare, INF);
        Arrays.fill(minTime, INF);

        minFare[start] = 0;
        minTime[start] = 0;
        pq.add(new Edge(start, 0, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.to == end) {
                return new long[]{minFare[end], minTime[end]};
            }

            // 현재 노드에서 연결된 간선 탐색
            for (Edge next : graph[curr.to]) {
                long newFare = minFare[curr.to] + next.fare;
                int newTime = (int) (minTime[curr.to] + next.time);

                if (newFare < minFare[next.to] || 
                    (newFare == minFare[next.to] && newTime < minTime[next.to])) {
                    minFare[next.to] = newFare;
                    minTime[next.to] = newTime;
                    pq.add(new Edge(next.to, newFare, newTime));
                }
            }
        }

        // 경로가 없을 때
        return new long[]{-1, -1};
    }
}