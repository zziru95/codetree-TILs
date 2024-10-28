import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int to;
        double L, C;

        public Node(int to, double L, double C) {
            this.to = to;
            this.L = L;
            this.C = C;
        }
    }

    static class State implements Comparable<State> {
        int node;
        double A, B, time;

        public State(int node, double A, double B) {
            this.node = node;
            this.A = A;
            this.B = B;
            this.time = B + X / A;
        }

        public int compareTo(State o) {
            return Double.compare(this.time, o.time);
        }
    }

    static int N, M;
    static double X;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Double.parseDouble(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            double L = Double.parseDouble(st.nextToken());
            double C = Double.parseDouble(st.nextToken());
            graph[now].add(new Node(next, L, C));
            graph[next].add(new Node(now, L, C));
        }

        dijkstra();
    }

    public static void dijkstra() {
        // 각 노드에서 A (최소 C 값) 에 따른 B (L 값의 합) 을 저장하는 맵을 유지합니다.
        Map<Double, Double>[] dist = new HashMap[N + 1];
        for (int i = 0; i <= N; i++) {
            dist[i] = new HashMap<>();
        }

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(1, Double.MAX_VALUE, 0.0));
        dist[1].put(Double.MAX_VALUE, 0.0);

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int now = curr.node;
            double A = curr.A;
            double B = curr.B;

            // 이미 더 작은 B 값이 존재하는 경우 건너뜁니다.
            if (dist[now].get(A) < B) {
                continue;
            }

            for (Node temp : graph[now]) {
                int next = temp.to;
                double L = temp.L;
                double C = temp.C;
                double newA = Math.min(A, C);
                double newB = B + L;

                // 새로운 시간 계산
                double newTime = newB + X / newA;

                // 기존에 같은 A 값에 대해 더 큰 B 값이 있거나, 해당 A 값이 없는 경우 업데이트
                if (!dist[next].containsKey(newA) || dist[next].get(newA) > newB) {
                    dist[next].put(newA, newB);
                    pq.add(new State(next, newA, newB));
                }
            }
        }

        // 목적지 N 에서 최소 시간을 찾습니다.
        double result = Double.MAX_VALUE;
        for (Map.Entry<Double, Double> entry : dist[N].entrySet()) {
            double A = entry.getKey();
            double B = entry.getValue();
            double time = B + X / A;
            result = Math.min(result, time);
        }

        result = Math.floor(result);
        System.out.print((int) result);
    }
}