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


    static int n,m,start, end;
    static int[][] graph;
    static int[] dist;
    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n+1][n+1];
        dist = new int[n+1];
        Arrays.fill(dist,INF);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[s][e] = w;
            graph[e][s] = w;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        ArrayList<Integer> answer = dijk(start, end);
        StringBuilder sb = new StringBuilder();
        sb.append(dist[start]).append('\n');
        for(int temp: answer){
            sb.append(temp).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }


    public static ArrayList<Integer> dijk(int start, int end){
        ArrayList<Integer> answer = new ArrayList<>();
        dist[end] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        pq.add(new Pair(end,0));

        while(!pq.isEmpty()){
            Pair current = pq.poll();
            int now = current.to;
            int w= current.w;
            if(dist[now]< w) continue;
            if(now == start) break;

            for(int i=1; i<n+1; i++){
                if(graph[now][i] !=0){
                    int newW = w + graph[now][i];
                    if(dist[i]>newW){
                        dist[i] = newW;
                        pq.add(new Pair(i,newW));
                    }
                }
            }
        }


        int x = end;
        answer.add(x);
        boolean[] visited = new boolean[n+1];
        visited[x] = true;
        while(x != start){
            for(int i=1; i<= n; i++){
                if(graph[i][x] ==0) continue;
                if(visited[i]) continue;
                if(dist[i]+ graph[i][x] == dist[x]) {
                    x=i;
                    visited[x] = true;
                    break;
                }
            }
            answer.add(x);
        }



        return answer;

    }
}