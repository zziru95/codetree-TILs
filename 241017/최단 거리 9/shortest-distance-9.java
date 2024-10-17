import java.util.*;
import java.io.*;


public class Main {
    static class Pair implements Comparable<Pair>{
        int to;
        int w;

        public Pair(int to, int w){
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o){
            return this.w-o.w;
        }
    }
    static int n,m,start,end;

    static ArrayList<Pair>[] graph;
    static int[] path, dist;
    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        dist = new int[n+1];
        path = new int[n+1];
        Arrays.fill(dist,INF);
        Arrays.fill(path,-1);


        for(int i=1; i<n+1;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[e].add(new Pair(s,w));
            graph[s].add(new Pair(e,w));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        ArrayList<Integer> answer = dijk(start,end);
        StringBuilder sb = new StringBuilder();
        sb.append(dist[start]).append('\n');
        for(int temp : answer){
            sb.append(temp).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }


    public static ArrayList<Integer> dijk(int start, int end){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(start);
        dist[end] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(end,0));

        while(!pq.isEmpty()){
            Pair current = pq.poll();
            int now = current.to;
            int w = current.w;
            if(w> dist[now]) continue;
            if(now == start) break;

            for(Pair temp2 : graph[now]){
                int next = temp2.to;
                int newW = w + temp2.w;
                if(dist[next]> newW){
                    dist[next] = newW;
                    path[next] = now;
                    pq.add(new Pair(next,newW));
                }
            }
        }


        while(start !=end){
            start = path[start];
            temp.add(start);
        }
        

        return temp;
    }
}