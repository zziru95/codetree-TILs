import java.util.*;
import java.io.*;



public class Main {
    static class Node{
        int to;
        long w;
        public Node(int to, long w){
            this.to = to;
            this.w = w;
        }
    }

    static class Edge{
        int to;
        long w;

        public Edge(int to, long w){
            this.to = to;
            this.w = w;
        }
    }

    static int N,M;
    static int S,E;
    static long INF = Long.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static long minD = Long.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i=0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            graph[s1].add(new Node(s2,w));
            graph[s2].add(new Node(s1,w));
        }


        long[] resultS = dijk(S);
        long[] resultE = dijk(E);

        for(int i=1; i<=N; i++){
            if(i==S || i==E) continue;
            long calD = resultS[i]+ resultE[i] + resultS[E];
            minD = Math.min(minD,calD);
        }


        System.out.print(minD);


    }


    public static long[] dijk(int start){
        long[] dist = new long[N+1];


        Arrays.fill(dist,INF);
  

        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)-> Long.compare(o1.w,o2.w));
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int now = curr.to;
            long w = curr.w;
            if(dist[now]<w) continue;
            for(Node temp : graph[now]){
                int next = temp.to;
                long newW = w + temp.w;
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Edge(next,newW));
                }
            }
        }

        return dist;
        

    }
}