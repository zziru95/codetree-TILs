import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to, w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    static int n, m;
    static ArrayList<HashMap<Integer, Integer>> graph;
    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<Integer>[] prevNodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        dist = new int[n + 1];
        prevNodes = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new HashMap<>());
            prevNodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s1).put(s2, w);
            graph.get(s2).put(s1, w);
        }

        dijkstra(1);

        int originalDistance = dist[n];
        HashSet<String> edgesInShortestPaths = new HashSet<>();
        collectEdges(n, edgesInShortestPaths);

        int count = 0;
        for (String edgeKey : edgesInShortestPaths) {
            String[] nodes = edgeKey.split("-");
            int u = Integer.parseInt(nodes[0]);
            int v = Integer.parseInt(nodes[1]);
            int temp = graph.get(u).get(v);

            graph.get(u).remove(v);
            graph.get(v).remove(u);

            dijkstra(1);

            if (dist[n] > originalDistance || dist[n] == INF) {
                count++;
            }

            graph.get(u).put(v, temp);
            graph.get(v).put(u, temp);
        }

        System.out.println(count);
    }

    public static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 0; i <= n; i++) {
            prevNodes[i].clear();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int now = curr.to;
            int w = curr.w;

            if (dist[now] < w) continue;

            for (int next : graph.get(now).keySet()) {
                int newW = graph.get(now).get(next) + w;

                if (dist[next] > newW) {
                    dist[next] = newW;
                    pq.add(new Edge(next, newW));
                    prevNodes[next].clear();
                    prevNodes[next].add(now);
                } else if (dist[next] == newW) {
                    prevNodes[next].add(now);
                }
            }
        }
    }

    public static void collectEdges(int node, HashSet<String> edges) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int prev : prevNodes[current]) {
                String edgeKey = Math.min(current, prev) + "-" + Math.max(current, prev);
                if (!edges.contains(edgeKey)) {
                    edges.add(edgeKey);
                    if (!visited[prev]) {
                        visited[prev] = true;
                        queue.add(prev);
                    }
                }
            }
        }
    }
}