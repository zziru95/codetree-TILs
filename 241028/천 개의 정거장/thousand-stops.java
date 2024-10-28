import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int to;
        long w;
        int time;

        public Pair(int to, long w, int time) {
            this.to = to;
            this.w = w;
            this.time = time;
        }
    }

    static class Node {
        int to;
        long w;
        long time;

        public Node(int to, long w, long time) {
            this.to = to;
            this.w = w;
            this.time = time;
        }
    }

    static int A, B, N;
    static List<Pair>[] graph;
    static long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList[1001];
        for (int i = 0; i < 1001; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long price = Long.parseLong(st.nextToken());
            int stopCnt = Integer.parseInt(st.nextToken());

            int[] route = new int[stopCnt];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < stopCnt; j++) {
                route[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < stopCnt - 1; j++) {
                for (int k = j + 1; k < stopCnt; k++) {
                    int time = k - j;
                    graph[route[j]].add(new Pair(route[k], price, time));
                }
            }
        }

        long[] answer = dijkstra(A, B);
        System.out.println(answer[0] + " " + answer[1]);
    }

    public static long[] dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.w == o2.w) {
                return Long.compare(o1.time, o2.time);
            }
            return Long.compare(o1.w, o2.w);
        });

        long[] minCost = new long[1001];
        long[] minTime = new long[1001];
        Arrays.fill(minCost, INF);
        Arrays.fill(minTime, INF);

        minCost[start] = 0;
        minTime[start] = 0;
        pq.add(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.to == end) {
                return new long[]{curr.w, curr.time};
            }

            for (Pair next : graph[curr.to]) {
                long newCost = curr.w + next.w;
                long newTime = curr.time + next.time;

                if (newCost < minCost[next.to] || (newCost == minCost[next.to] && newTime < minTime[next.to])) {
                    minCost[next.to] = newCost;
                    minTime[next.to] = newTime;
                    pq.add(new Node(next.to, newCost, newTime));
                }
            }
        }

        return new long[]{-1, -1};
    }
}