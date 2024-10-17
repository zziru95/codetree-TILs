import java.util.*;
import java.io.*;

public class Main  {
    static class Pair {
        int to;
        int w;

        public Pair(int to, int w){
            this.to = to;
            this.w = w;
        }

    }


    static int n,m,k;
    static ArrayList<Pair>[] graph;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[start].add(new Pair(end, w));
            graph[end].add(new Pair(start, w));
        }


        int[] dist= dijk(k);

        StringBuilder sb =new StringBuilder();
        for(int i=1; i<n+1; i++){
            sb.append((dist[i] != (int) 1e9) ? dist[i] : -1);
            if(i<n) sb.append('\n');
        }

        System.out.print(sb);


    }

    public static int[] dijk(int start){
        int[] dist = new int[n+1];
        Arrays.fill(dist, (int) 1e9);
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2) -> o1.w-o2.w);
        pq.add(new Pair(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Pair current = pq.poll();
            int to = current.to;
            int w = current.w;
            if(w>dist[to]) continue;

            for(Pair temp: graph[to]){
                int newW = w+ temp.w;
                int next = temp.to;
                if(dist[next]> newW){
                    dist[next] = newW;
                    pq.add(new Pair(next,newW));
                }
            }
        }






        return dist;
    }
}