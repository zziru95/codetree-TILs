import java.util.*;
import java.io.*;

public class Main {

    static class Node{
        int to;
        double L,C;

        public Node(int to, double L, double C){
            this.to = to;
            this.L = L;
            this.C = C;
        }
    }

    static class Pair implements Comparable<Pair>{
        int to;
        double A,B,W;

        public Pair(int to, double A, double B){
            this.to = to;
            this.A = A;
            this.B = B;
            this.W = B + X/A;
        }
        public Pair(int to, double A, double B, double W){
            this.to = to;
            this.A = A;
            this.B = B;
            this.W = W;
        }

        public int compareTo(Pair o){
            return Double.compare(this.W, o.W);
        }
    }

    static int N,M;
    static double X;
    static double INF = Double.MAX_VALUE;
    static ArrayList<Node>[] graph;


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Double.parseDouble(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            double L = Double.parseDouble(st.nextToken());
            double C = Double.parseDouble(st.nextToken());
            graph[now].add(new Node(next,L,C));
            graph[next].add(new Node(now,L,C));
        }

        dijk();
    }


    public static void dijk(){
        double[] dist = new double[N+1];
        Arrays.fill(dist,INF);
        dist[1] = 0.0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1,INF,0,0));
        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int now = curr.to;
            double A = curr.A;
            double B = curr.B;
            double W = curr.W;
            
            if(dist[now]<W) continue;
            for(Node temp : graph[now]){
                int next = temp.to;
                double L = temp.L;
                double C = temp.C;
                Pair nextPair = new Pair(next,Math.min(C,A),B+L);
                if(dist[next]>= nextPair.W){
                    dist[next] = nextPair.W;
                    pq.add(nextPair);
                }
            }
        }
        double result = Math.floor(dist[N]*10)/10;
        System.out.print((int) result);
        
    }
}