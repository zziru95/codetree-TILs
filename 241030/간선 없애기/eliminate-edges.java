import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int to, w;

        public Edge(int to, int w){
            this.to = to;
            this.w = w;
        }

        public int compareTo(Edge o){
            return this.w- o.w;
        }
    }


    static int n,m;
    static ArrayList<HashMap<Integer,Integer>> graph;
    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    static int[] beforeNode;
    static HashSet<Integer> shorts = new HashSet<>();

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        dist = new int[n+1];
        beforeNode = new int[n+1];
        for(int i=0; i<=n; i++){
            graph.add(new HashMap<>());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s1).put(s2,w);
            graph.get(s2).put(s1,w);
        }

        dijk(1);

        ArrayList<Integer> path = new ArrayList<>();
        int currentNode = n;
        while(currentNode != 1){
            path.add(currentNode);
            currentNode = beforeNode[currentNode];
        }
        path.add(1);

        for(int i= path.size()-1; i>0; i--){
            int now = path.get(i);
            int before = path.get(i-1);

            int temp = graph.get(now).get(before);
            graph.get(now).remove(before);
            graph.get(before).remove(now);
            dijk(1);
            graph.get(now).put(before,temp);
            graph.get(before).put(now,temp);
        }

        System.out.print(shorts.size()-1);
    }

    public static void dijk(int start){
        Arrays.fill(dist,INF);
        dist[start] = 0;
        beforeNode[start] = start;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int now = curr.to;
            int w = curr.w;

            if(now == n){
                shorts.add(w);
                return;
            }

            if(dist[now]<w) continue;

            for(int next : graph.get(now).keySet()){
                int newW = graph.get(now).get(next) + w;
                if(dist[next]> newW){
                    dist[next] = newW;
                    pq.add(new Edge(next, newW));
                    
                    beforeNode[next] = now;
                }
            }
        }


    }
}