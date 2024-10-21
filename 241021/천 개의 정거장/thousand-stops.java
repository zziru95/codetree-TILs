import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int to;
        int w;
        int bus;
        public Pair(int to, int w, int bus){
            this.to = to;
            this.w = w;
            this.bus = bus;
        }
    }

    static class Node{
        int to;
        int w;
        int bus;
        int time;
        public Node(int to, int w, int bus, int time){
            this.to = to;
            this.w = w;
            this.bus = bus;
            this.time = time;
        }
    }
    static int A,B,N;
    static HashMap<Integer,ArrayList<Pair>> graph;
    static int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new HashMap<>();
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N= Integer.parseInt(st.nextToken());
        for(int i=1; i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int stopCnt = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int next = -1;
            while(st.hasMoreTokens()){
                next = Integer.parseInt(st.nextToken());
                if(next != -1){
                    graph.putIfAbsent(start,new ArrayList<>());
                    graph.get(start).add(new Pair(next,price,i));
                }
                start = next;
            }
        }



        int[] answer = dijk(A,B);
        System.out.print(answer[0]+" "+answer[1]);
    }


    public static int[] dijk(int A, int B){
        int[] answer = new int[] {-1,-1};
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->  {
            if(o1.w==o2.w){
                return o1.time-o2.time;
            }
            return o1.w-o2.w;
        });
        int[] dist = new int[1001];
        Arrays.fill(dist,INF);
        dist[A] = 0;
        pq.add(new Node(A,0,0,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int now = curr.to;
            int w = curr.w;
            int bus = curr.bus;
            int time = curr.time;

            // if(dist[now]< w) continue;
            if(now == B) {
                answer[0] = w;
                answer[1] = time;
                break;
            }
            if(!graph.containsKey(now)) continue;
            for(Pair temp :graph.get(now)){
                int next = temp.to;
                int nextBus = temp.bus;
                int newW = w;
                if(bus!=nextBus){
                    newW+=temp.w;
                }
                if(dist[next]> newW || dist[next] == newW){
                    dist[next] = newW;
                    pq.add(new Node(next,newW,nextBus,time+1));
                }
            }
        }


        return answer;
    }
}