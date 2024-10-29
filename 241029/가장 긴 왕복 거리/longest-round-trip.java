import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int to, w;
        public Node(int to, int w){
            this.to = to;
            this.w = w;
        }
    }

    static class Pair implements Comparable<Pair>{
        int to, w;
        public Pair(int to, int w){
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o){
            return this.w-o.w;
        }
    }
    static int n,m,x;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static ArrayList<Node>[] graph2;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        graph2 = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s1].add(new Node(s2,w));
            graph2[s2].add(new Node(s1,w));
        }

        int[] result = dijk(x);
        int[] result2 =dijk2(x);

        int maxD = -1;

        for(int i=1; i<=n; i++){
            if( i== x) continue;
            if(result[i] == INF || result2[i] == INF ) continue;
            maxD = Math.max(maxD, (result[i] + result2[i]));
        }

        System.out.print(maxD);


    }



    public static int[] dijk(int start){
        int[] dist = new int[n+1];
        Arrays.fill(dist,INF);
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start,0));

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int now = curr.to;
            int w = curr.w;

            if(dist[now]>w) continue;

            for(Node temp : graph[now]){
                int next = temp.to;
                int newW = w + temp.w;
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Pair(next,newW));
                }
            }

        }

        return dist;
    }

    public static int[] dijk2(int start){
        int[] dist = new int[n+1];
        Arrays.fill(dist,INF);
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start,0));

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int now = curr.to;
            int w = curr.w;

            if(dist[now]>w) continue;

            for(Node temp : graph2[now]){
                int next = temp.to;
                int newW = w + temp.w;
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Pair(next,newW));
                }
            }

        }

        return dist;
    }
}