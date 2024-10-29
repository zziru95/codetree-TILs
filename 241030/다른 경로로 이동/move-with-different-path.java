import java.util.*;
import java.io.*;


public class Main {
    static class Node{
        int to;
        int w;
        public Node(int to, int w){
            this.to = to;
            this.w = w;
        }
    }

    static class Edge implements Comparable<Edge>{
        int to;
        int w;
        public Edge(int to, int w){
            this.to =to;
            this.w = w;
        }

        public int compareTo(Edge o){
            if(this.w == o.w){
                return this.to-o.to;
            }
            return this.w-o.w;
        }
    }


    static int n,m;
    static int[] dist, beforeNode;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;



    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        dist = new int[n+1];
        beforeNode = new int[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e,w));
            graph[e].add(new Node(s,w));
        }

        ArrayList<Integer> path = new ArrayList();
        int result = dijk();
        int currentNode = n;
        while(currentNode !=1){
            path.add(currentNode);
            currentNode = beforeNode[currentNode];
        }
        path.add(1);

        for (int i = path.size() - 1; i > 0; i--) {
            int start = path.get(i);
            int end = path.get(i - 1);

            // start 노드에서 end로 가는 간선 제거
            graph[start].removeIf(node -> node.to == end);
            // end 노드에서 start로 가는 간선 제거
            graph[end].removeIf(node -> node.to == start);
        }
        
        System.out.print(dijk());

    }


    public static int dijk(){
        Arrays.fill(dist,INF);
        Arrays.fill(beforeNode,-1);
        dist[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0));
        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            int now = curr.to;
            int w = curr.w;
            
            if(now == n) return w;
            if(dist[now]<w) continue;

            for(Node temp : graph[now]){
                int next = temp.to;
                int newW = w + temp.w;

                if(dist[next]>newW){
                    dist[next] = newW;
                    pq.add(new Edge(next,newW));
                    beforeNode[next] = now;
                }
            }
        }

        return -1;
    }

}