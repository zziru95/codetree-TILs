import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int to;
        long w;
        int bus;
        int time;
        public Pair(int to, long w, int bus, int time){
            this.to = to;
            this.w = w;
            this.bus = bus;
            this.time = time;
        }
    }

    static class Node{
        int to;
        long w;
        int bus;
        long time;
        public Node(int to, long w, int bus, long time){
            this.to = to;
            this.w = w;
            this.bus = bus;
            this.time = time;
        }
    }

    static class Cost{
        long w;
        long time;
        public Cost(long w, long time){
            this.w = w;
            this.time = time;
        }
    }

    static int A,B,N;
    static HashMap<Integer,ArrayList<Pair>> graph;
    static long INF = (long)1e11;

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
            long price = Long.parseLong(st.nextToken());
            int stopCnt = Integer.parseInt(st.nextToken());
            int[] route = new int[stopCnt];
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<stopCnt;j++) {
                route[j] = Integer.parseInt(st.nextToken());
            }
            for(int j = 0; j < stopCnt - 1; j++){
                long cumulativeTime = 0;
                for(int k = j + 1; k < stopCnt; k++){
                    cumulativeTime += 1; // 각 정류장 간 시간 (1로 가정)
                    graph.putIfAbsent(route[j], new ArrayList<>());
                    graph.get(route[j]).add(new Pair(route[k], price, i, (int)cumulativeTime));
                }
            }

        }

        long[] answer = dijk(A,B);
        System.out.print(answer[0]+" "+answer[1]);
    }


    public static long[] dijk(int A, int B){
        long[] answer = new long[] {-1,-1};
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
        int cmp = Long.compare(o1.w, o2.w);
        if (cmp == 0) {
            return Long.compare(o1.time, o2.time);
        }
        return cmp;
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
            long w = curr.w;
            int bus = curr.bus;
            long time = curr.time;

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
                long newW = w;
                long newTime = time + temp.time;
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