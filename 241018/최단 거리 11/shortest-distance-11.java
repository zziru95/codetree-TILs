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
    static ArrayList<Pair>[] graph;
    static int[] dist;
    static int[] pre;
    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        dist = new int[n+1];
        pre = new int[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }
        Arrays.fill(pre,-1);
        Arrays.fill(dist,INF);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[s].add(new Pair(e,w));
            graph[e].add(new Pair(s,w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        ArrayList<Integer> answer = dijk(start, end);
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append('\n');
        for(int temp: answer){
            sb.append(temp).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }


    public static ArrayList<Integer> dijk(int start, int end){
        ArrayList<Integer> answer = new ArrayList<>();
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        pq.add(new Pair(start,0));

        while(!pq.isEmpty()){
            Pair current = pq.poll();
            int now = current.to;
            int w= current.w;
            if(dist[now]< w) continue;
            if(now == end) break;

            for(Pair temp : graph[now]){
                int next = temp.to;
                int newW = temp.w + w;
                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Pair(next,newW));
                    pre[next] = now;
                }

                if(dist[next]==newW){
                    if(pre[next]< now){
                        dist[next] = newW;
                        pre[next] = now;
                    }
                }
            }
        }


        int x = end;
        answer.add(x);

        while(x != start){
            if(x== -1) break;
            x=pre[x];
            answer.add(x);
        }

        Collections.reverse(answer);
        return answer;

    }
}