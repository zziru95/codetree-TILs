import java.util.*;
import java.io.*;

public class Main {
    static class Pair{
        int to,w;
        public Pair(int to, int w){
            this.to = to;
            this.w = w;
        }
    }
    static int n,m,a,b,c;
    static int INF =(int) 1e9;
    static int maxD = -1;
    static ArrayList<Pair>[] graph;
    static Set<Integer> points;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        graph = new ArrayList[n+1];
        for(int i=0; i<n+1;i++){
            graph[i] = new ArrayList<>();
        }

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());


        for(int i=0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Pair(e,w));
            graph[e].add(new Pair(s,w));
        }

        int[] dist = dijk();
        
        for(int i=1; i<=n; i++){
            maxD = Math.max(maxD,dist[i]);
        }

        System.out.print(maxD);
    }



    public static int[] dijk(){
        int[] dist = new int[n+1];
        Arrays.fill(dist,INF);
        dist[a] = 0;
        dist[b] = 0;
        dist[c] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        pq.add(new Pair(a,0));
        pq.add(new Pair(b,0));
        pq.add(new Pair(c,0));

        int minD = INF;

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int now = curr.to;
            int w = curr.w;
            if(dist[now]> w) continue;
            for(Pair temp : graph[now]){
                int next = temp.to;
                int newW = w+ temp.w;
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Pair(next,newW));
                }
            }
        }
        return dist;
    }
}