import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        long distance;

        public Node(int id, long distance){
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other){
            if(this.distance != other.distance){
                return Long.compare(this.distance, other.distance);
            }
            return Integer.compare(this.id, other.id);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수

        // 인접 리스트 초기화
        List<Edge>[] adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();

        // 간선 정보 입력
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
        }

        // A와 B 정점 입력
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 거리 배열과 전임자 배열 초기화
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        int[] predecessor = new int[n+1];
        Arrays.fill(predecessor, -1);

        // 경로 배열 초기화
        List<Integer>[] path = new ArrayList[n+1];
        for(int i=0; i<=n; i++) path[i] = new ArrayList<>();

        // 우선순위 큐 초기화
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작 정점 초기화
        dist[A] = 0;
        path[A].add(A);
        pq.add(new Node(A, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int u = current.id;
            long currentDist = current.distance;

            // 이미 더 짧은 거리가 발견된 경우 무시
            if(currentDist > dist[u]){
                continue;
            }

            for(Edge edge : adj[u]){
                int v = edge.to;
                long newDist = currentDist + edge.weight;

                if(newDist < dist[v]){
                    // 더 짧은 거리 발견
                    dist[v] = newDist;
                    predecessor[v] = u;
                    
                    // 경로 업데이트: path[v] = path[u] + v
                    path[v] = new ArrayList<>(path[u]);
                    path[v].add(v);
                    
                    pq.add(new Node(v, newDist));
                }
                else if(newDist == dist[v]){
                    // 거리 동일, 사전 순 비교
                    // 새로 발견된 경로: path[u] + v
                    List<Integer> candidatePath = new ArrayList<>(path[u]);
                    candidatePath.add(v);
                    
                    // 기존 경로: path[v]
                    if(isLexSmaller(candidatePath, path[v])){
                        // 새 경로가 사전 순으로 더 작으면 업데이트
                        predecessor[v] = u;
                        path[v] = candidatePath;
                        pq.add(new Node(v, newDist));
                    }
                }
            }
        }

        // 최단 거리 출력
        System.out.println(dist[B]);

        // 경로 추적
        List<Integer> resultPath = path[B];
        
        // 경로가 A에서 시작하는지 확인
        if(resultPath.isEmpty() || resultPath.get(0) != A){
            // A에서 B로 가는 경로가 없는 경우
            System.out.println("경로가 존재하지 않습니다.");
            return;
        }

        // 경로 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<resultPath.size(); i++){
            sb.append(resultPath.get(i));
            if(i != resultPath.size()-1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    // 두 경로를 비교하여 path1이 path2보다 사전 순으로 작은지 확인하는 함수
    private static boolean isLexSmaller(List<Integer> path1, List<Integer> path2){
        int size1 = path1.size();
        int size2 = path2.size();
        int minSize = Math.min(size1, size2);
        for(int i=0; i<minSize; i++){
            if(!path1.get(i).equals(path2.get(i))){
                return path1.get(i) < path2.get(i);
            }
        }
        return size1 < size2;
    }
}