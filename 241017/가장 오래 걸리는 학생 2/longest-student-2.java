import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int to;
        int w;

        public Pair(int to, int w){
            this.to = to;
            this.w = w;
        }


    }

    static int N,M;
    static ArrayList<Pair>[] graph;
    static int INF = (int) 1e9;
    
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[e].add(new Pair(s,w));
        }

        System.out.print(dijk(N));

        
    }


    public static int dijk(int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist,INF);
        dist[start] = 0;
        int maxD = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2) -> {
            return o1.w - o2.w;
        });
        pq.add(new Pair(start,0));

        while(!pq.isEmpty()){
            Pair current = pq.poll();
            int now = current.to;
            int w = current.w;
            if(w> dist[now]) continue;
            if(w != INF){
                maxD = Math.max(maxD, w);
            }
            for(Pair temp : graph[now]){
                int next = temp.to;
                int newW = temp.w + w;
                if(dist[next] > newW){
                    dist[next] = newW;
                    pq.add(new Pair(next, newW));
                }
            }
        }

        return maxD;
    }
}