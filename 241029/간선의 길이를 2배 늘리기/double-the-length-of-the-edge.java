import java.util.*;
import java.io.*;


public class Main {

    static class Edge{
        int to;
        int w;
        public Edge(int to, int w){
            this.to = to;
            this.w = w;

        }
    }

    static int N,M;
    static ArrayList<HashMap<Integer,Integer>> graph;
    static int INF = Integer.MAX_VALUE;
    static int maxD = -1;
    static int[] dist, before;


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        dist = new int[N+1];
        before = new int[N+1];
        for(int i=0; i<=N; i++){
            graph.add(new HashMap<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).put(e,w);
            graph.get(e).put(s,w);
        }


        dijk(1);

        int firstD =dist[N];
        ArrayList<Integer> path = new ArrayList<>();
        
        int currentNode = N;
        while(currentNode !=1){
            path.add(currentNode);
            currentNode = before[currentNode];
        }
        path.add(1);

        for(int i=1; i<path.size(); i++){
            int start = path.get(i-1);
            int end = path.get(i);
            
            int temp = graph.get(start).get(end);
            graph.get(start).put(end,temp*2);
            graph.get(end).put(start,temp*2);
            dijk(1);
            maxD = Math.max(maxD,dist[N]);

            graph.get(start).put(end,temp);
            graph.get(end).put(start,temp);
        }

        System.out.print((maxD-firstD));
    }



    public static void dijk(int start){
        Arrays.fill(dist,INF);
        Arrays.fill(before,-1);
        dist[start] = 0;
        before[start] = start;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);

        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int now = curr.to;
            int w = curr.w;

            if(now == N){
                return;
            }


            if(dist[now]>w) continue;

            for(int next: graph.get(now).keySet()){
                int newW = w+ graph.get(now).get(next);
                
                if(dist[next]>newW){
                    dist[next] = newW;
                    before[next] = now;
                    pq.add(new Edge(next,newW));
                }
            }
        }
    }
}