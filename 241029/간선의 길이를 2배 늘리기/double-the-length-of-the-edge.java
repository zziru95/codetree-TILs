import java.util.*;
import java.io.*;


public class Main {

    static class Edge{
        int to;
        int w;
        ArrayList<Integer> path;

        public Edge(int to, int w, ArrayList path){
            this.to = to;
            this.w = w;
            this.path = path;
        }
    }

        static class Edge2{
        int to;
        int w;

        public Edge2(int to, int w){
            this.to = to;
            this.w = w;
        }
    }

    static int N,M;
    static ArrayList<HashMap<Integer,Integer>> graph;
    static int INF = Integer.MAX_VALUE;
    static int maxD = -1;


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
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


        Edge result = dijk(1);
        int firstD = result.w;

        for(int i=1; i<result.path.size(); i++){
            int start = result.path.get(i-1);
            int end = result.path.get(i);
            
            int temp = graph.get(start).get(end);
            graph.get(start).put(end,temp*2);
            graph.get(end).put(start,temp*2);
            
            maxD = Math.max(maxD, dijk2(1));

            graph.get(start).put(end,temp);
            graph.get(end).put(start,temp);
        }

        System.out.print((maxD-firstD));
    }



    public static Edge dijk(int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist,INF);
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        ArrayList<Integer> init = new ArrayList<>();
        init.add(start);
        pq.add(new Edge(start,0,init));

        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int now = curr.to;
            int w = curr.w;
            ArrayList<Integer> path = curr.path;

            if(now == N){
                return curr;
            }


            if(dist[now]>w) continue;

            for(int next: graph.get(now).keySet()){
                int newW = w+ graph.get(now).get(next);
                
                if(dist[next]>newW){
                    ArrayList<Integer> newPath = new ArrayList<>(path);
                    newPath.add(next);
                    dist[next] = newW;
                    pq.add(new Edge(next,newW,newPath));
                }
            }
        }


        return new Edge(-1,-1,new ArrayList<>());
    }

        public static int dijk2(int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist,INF);
        dist[start] = 0;
        PriorityQueue<Edge2> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        pq.add(new Edge2(start,0));

        while(!pq.isEmpty()){
            Edge2 curr = pq.poll();
            int now = curr.to;
            int w = curr.w;

            if(now == N){
                return w;
            }


            if(dist[now]>w) continue;

            for(int next: graph.get(now).keySet()){
                int newW = w+ graph.get(now).get(next);
                
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Edge2(next,newW));
                }
            }
        }


        return -1;
    }
}