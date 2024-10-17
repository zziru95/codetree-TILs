import java.util.*;
import java.io.*;

public class Main {
    static class Edge implements Comparable<Edge>{
        int next;
        int weight;

        public Edge(int next, int weight){
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight- o.weight;
        }
    }

    static int n,m;
    static int[] distance;
    static Map<Integer,List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph= new HashMap<>();
        distance = new int[n+1];
        for(int i=0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(s,new ArrayList<>());
            graph.get(s).add(new Edge(t,w));
        }

        dijkstra(1);
        for(int i=2; i<=n; i++){
            System.out.println((distance[i] != Integer.MAX_VALUE) ? distance[i] : -1);
        }
    }

    public static void dijkstra(int start){
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start ,0));

        while(!pq.isEmpty()){
            Edge current = pq.poll();
            int now = current.next;
            int weight = current.weight;
            if(weight> distance[now]) continue;

            for(Edge to : graph.getOrDefault(now,new ArrayList<>())){
                int newDistance = weight+to.weight;
                if(distance[to.next]>newDistance){
                    distance[to.next] = newDistance;
                    pq.add(new Edge(to.next,newDistance));
                }
            }
        }

    }
}