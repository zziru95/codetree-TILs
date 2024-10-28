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
    static ArrayList<HashMap<Integer, Pair>>[] graph;
    static long INF = (long) 1e11;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList[1001];  // 각 정류장에 대한 ArrayList 초기화
        for (int i = 0; i < 1001; i++) {
            graph[i] = new ArrayList<>();  // 각 정류장에 ArrayList 생성
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
                    cumulativeTime += 1;  // 각 정류장 간 시간 (1로 가정)
                    if (check(route[j], route[k], price, cumulativeTime)) {
                        HashMap<Integer, Pair> map = new HashMap<>();
                        map.put(route[k], new Pair(route[k], price, i, (int) cumulativeTime));
                        graph[route[j]].add(map);  // 그래프에 경로 추가
                    }
                }
            }
        }

        long[] answer = dijk(A, B);
        System.out.print(answer[0] + " " + answer[1]);
    }

    public static long[] dijk(int A, int B) {
        long[] answer = new long[] { -1, -1 };
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            int cmp = Long.compare(o1.w, o2.w);
            if (cmp == 0) {
                return Long.compare(o1.time, o2.time);
            }
            return cmp;
        });

        Cost[] dist = new Cost[1001];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = new Cost(INF, INF);
        }
        dist[A] = new Cost(0, 0);
        pq.add(new Node(A, 0, 0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.to;
            long w = curr.w;
            int bus = curr.bus;
            long time = curr.time;

            if (compare(new Cost(w, time), dist[now])) continue;
            if (now == B) {
                answer[0] = w;
                answer[1] = time;
                break;
            }

            for (HashMap<Integer, Pair> map : graph[now]) {
                for (Map.Entry<Integer, Pair> entry : map.entrySet()) {
                    int next = entry.getKey();
                    Pair temp = entry.getValue();
                    int nextBus = temp.bus;
                    long newW = w;
                    long newTime = time + temp.time;
                    newW += temp.w;

                    Cost newCost = new Cost(newW, newTime);
                    if (compare(dist[next], newCost)) {
                        dist[next] = newCost;
                        pq.add(new Node(next, newW, nextBus, newTime));
                    }
                }
            }
        }

        return answer;
    }

    public static boolean compare(Cost o1, Cost o2) {
        return o1.w > o2.w || (o1.w == o2.w && o1.time > o2.time);
    }

    public static boolean check(int start, int to, long price, long time) {
        for (int i = graph[start].size() - 1; i >= 0; i--) {  // 뒤에서부터 순회
            HashMap<Integer, Pair> map = graph[start].get(i);
            if (map.containsKey(to)) {
                Pair temp = map.get(to);
                if (temp.w < price || (temp.w == price && temp.time < time)) {
                    return false;  // 더 빠르거나 저렴한 경로가 있으면 추가하지 않음
                }
                graph[start].remove(i);  // 기존 경로 삭제
            }
        }
        return true;
    }
}