import java.util.*;
import java.io.*;



public class Main {
    static int n,m;

    static class Edge {
        int next,weight;

        public Edge(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken())+1;
        m = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();

        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Edge(e, w));
            graph.get(e).add(new Edge(s, w));
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);



        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight,o2.weight));
        pq.add(new Edge(1,0));
        dist[1] = 0;
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.next;

            if(visited[u]) continue;
            visited[u] = true;

            for(Edge edge: graph.get(u)){
                int v = edge.next;
                int weight = edge.weight;

                if (!visited[v] && weight < dist[v]) {
                    dist[v] = weight;
                    pq.offer(new Edge(v, weight));
                }
            }
        }
        int answer =0;
        dist[0] = 0;
        for (int d : dist) {
            answer += d;
        }
        System.out.print(answer);
    }
}