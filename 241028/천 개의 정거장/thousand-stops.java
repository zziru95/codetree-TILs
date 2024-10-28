import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int to;
        long w;
        int bus;
        int time;

        public Pair(int to, long w, int bus, int time) {
            this.to = to;
            this.w = w;
            this.bus = bus;
            this.time = time;
        }
    }

    static class Node {
        int to;
        long w;
        int bus;
        long time;

        public Node(int to, long w, int bus, long time) {
            this.to = to;
            this.w = w;
            this.bus = bus;
            this.time = time;
        }
    }

    static class Cost {
        long w;
        long time;

        public Cost(long w, long time) {
            this.w = w;
            this.time = time;
        }
    }

    static int A, B, N;
    static HashMap<Integer, Pair>[] graph;
    static long INF = (long) 1e11;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        graph = new HashMap[1001];
        for (int i = 0; i < 1001; i++) {
            graph[i] = new HashMap<>();
        }

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            long price = Long.parseLong(st.nextToken());
            int stopCnt = Integer.parseInt(st.nextToken());
            int[] route = new int[stopCnt];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < stopCnt; j++) {
                route[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < stopCnt - 1; j++) {
                long cumulativeTime = 0;
                for (int k = j + 1; k < stopCnt; k++) {
                    cumulativeTime += 1;
                    updateGraph(route[j], route[k], price, cumulativeTime, i);
                }
            }
        }

        long[] answer = dijk(A, B);
        System.out.print(answer[0] + " " + answer[1]);
    }

    public static void updateGraph(int start, int to, long price, long time, int bus) {
        if (!graph[start].containsKey(to) || graph[start].get(to).w > price || (graph[start].get(to).w == price && graph[start].get(to).time > time)) {
            graph[start].put(to, new Pair(to, price, bus, (int) time));
        }
    }

    public static long[] dijk(int A, int B) {
        long[] answer = new long[]{-1, -1};
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            int cmp = Long.compare(o1.w, o2.w);
            if (cmp == 0) {
                return Long.compare(o1.time, o2.time);
            }
            return cmp;
        });

        Cost[] dist = new Cost[1001];
        boolean[] visited = new boolean[1001]; // 방문 여부 체크

        for (int i = 0; i < dist.length; i++) {
            dist[i] = new Cost(INF, INF);
        }
        dist[A] = new Cost(0, 0);
        pq.add(new Node(A, 0, 0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.to;

            if (visited[now]) continue; // 이미 방문한 정류장은 스킵
            visited[now] = true;

            if (now == B) {
                answer[0] = curr.w;
                answer[1] = curr.time;
                break;
            }

            for (Map.Entry<Integer, Pair> entry : graph[now].entrySet()) {
                int next = entry.getKey();
                Pair temp = entry.getValue();

                long newW = curr.w + temp.w;
                long newTime = curr.time + temp.time;

                if (compare(dist[next], new Cost(newW, newTime))) {
                    dist[next] = new Cost(newW, newTime);
                    pq.add(new Node(next, newW, temp.bus, newTime));
                }
            }
        }

        return answer;
    }

    public static boolean compare(Cost o1, Cost o2) {
        return o1.w > o2.w || (o1.w == o2.w && o1.time > o2.time);
    }
}