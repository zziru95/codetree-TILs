import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int to;
        int w;
        int bus;
        int time;
        public Pair(int to, int w, int bus, int time){
            this.to = to;
            this.w = w;
            this.bus = bus;
            this.time = time;
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

    static class Cost{
        int w;
        int time;
        public Cost(int w, int time){
            this.w = w;
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
            int[] route = new int[stopCnt];
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<stopCnt;j++) {
                route[j] = Integer.parseInt(st.nextToken());
            }
            for(int j=0; j<stopCnt-1;j++){
                for(int k=j+1; k<stopCnt;k++){
                    graph.putIfAbsent(route[j],new ArrayList<>());
                    graph.get(route[j]).add(new Pair(route[k],price,i,k-j));
                }
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
        Cost[] dist = new Cost[1001];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = new Cost(INF, INF);
        }
        dist[A] = new Cost(0,0);
        pq.add(new Node(A,0,0,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int now = curr.to;
            int w = curr.w;
            int bus = curr.bus;
            int time = curr.time;

            if(compare(new Cost(w,time),dist[now])) continue;
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
                int newTime = time + temp.time;
                if(bus!=nextBus){
                    newW+=temp.w;
                }
                Cost newCost = new Cost(newW,newTime);
                if(compare(dist[next],newCost)){
                    dist[next] = newCost;
                    pq.add(new Node(next,newW,nextBus,newTime));
                }
            }
        }


        return answer;
    }

    public static boolean compare(Cost o1, Cost o2) {
        return o1.w > o2.w || (o1.w == o2.w && o1.time > o2.time);
    }
}