import java.util.*;
import java.io.*;



public class Main {
    static class Node{
        int to;
        int w;
        public Node(int to, int w){
            this.to = to;
            this.w = w;
        }
    }

    static class Edge{
        int to;
        int w;

        public Edge(int to, int w){
            this.to = to;
            this.w = w;
        }
    }

    static int N,M;
    static int S,E;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static int minD = Integer.MAX_VALUE;


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
            int w = Integer.parseInt(st.nextToken());

            graph[s1].add(new Node(s2,w));
            graph[s2].add(new Node(s1,w));
        }


        int[] resultS = dijk(S);
        int[] resultE = dijk(E);

        for(int i=1; i<=N; i++){
            if(i==S || i==E) continue;
            int calD = resultS[i]+ resultE[i] + resultS[E];
            minD = Math.min(minD,calD);
        }


        System.out.print(minD);


    }


    public static int[] dijk(int start){
        int[] dist = new int[N+1];


        Arrays.fill(dist,INF);
  

        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int now = curr.to;
            int w = curr.w;
            if(dist[now]<w) continue;
            for(Node temp : graph[now]){
                int next = temp.to;
                int newW = w + temp.w;
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Edge(next,newW));
                }
            }
        }

        return dist;
        

    }
}