import java.util.*;
import java.io.*;
public class Main {

    static class Pair{
        int to;
        int w;
        public Pair(int to, int w){
            this.to = to;
            this.w = w;
        }
    }

    static int n,m,s,e;
    static ArrayList<Pair>[] graph;
    static int[] dist;
    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n+1];
        Arrays.fill(dist,INF);
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s1].add(new Pair(s2,w));
            graph[s2].add(new Pair(s1,w));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        dijk(s);
        System.out.print(dist[e]);

    }

    public static void dijk(int start){
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        pq.add(new Pair(start,0));

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int now = curr.to;
            int w = curr.w;
            if(dist[now]<w) continue;

            for(Pair temp: graph[now]){
                int next = temp.to;
                int newW = temp.w + w;
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Pair(next,newW));
                }
            }
        }


    }
}