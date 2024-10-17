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
        List<Integer> path;

        public Node(int id, long distance, List<Integer> path){
            this.id = id;
            this.distance = distance;
            this.path = path;
        }

        @Override
        public int compareTo(Node other){
            if(this.distance != other.distance){
                return Long.compare(this.distance, other.distance);
            }
            // Compare paths lex order
            int size1 = this.path.size();
            int size2 = other.path.size();
            int minSize = Math.min(size1, size2);
            for(int i=0; i<minSize; i++){
                if(!this.path.get(i).equals(other.path.get(i))){
                    return this.path.get(i) - other.path.get(i);
                }
            }
            return size1 - size2;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // number of nodes
        int m = Integer.parseInt(st.nextToken()); // number of edges

        // Initialize adjacency list
        List<Edge>[] adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++) adj[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
        }

        // Read A and B
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // Initialize distance array and path array
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        List<Integer>[] paths = new ArrayList[n+1];
        for(int i=0; i<=n; i++) paths[i] = new ArrayList<>();

        // Priority Queue
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Initialize source
        dist[A] = 0;
        paths[A].add(A);
        pq.add(new Node(A, 0, new ArrayList<>(paths[A])));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int u = current.id;
            long currentDist = current.distance;
            List<Integer> currentPath = current.path;

            // If we have already found a better path, skip
            if(currentDist > dist[u]){
                continue;
            }

            for(Edge edge : adj[u]){
                int v = edge.to;
                long newDist = currentDist + edge.weight;

                if(newDist < dist[v]){
                    // Found a better path
                    dist[v] = newDist;
                    // Update path
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(v);
                    paths[v] = newPath;
                    pq.add(new Node(v, newDist, newPath));
                }
                else if(newDist == dist[v]){
                    // Check if the new path is lex smaller
                    List<Integer> existingPath = paths[v];
                    List<Integer> candidatePath = new ArrayList<>(currentPath);
                    candidatePath.add(v);
                    if(isLexSmaller(candidatePath, existingPath)){
                        paths[v] = candidatePath;
                        pq.add(new Node(v, newDist, candidatePath));
                    }
                }
            }
        }

        // Output
        System.out.println(dist[B]);
        List<Integer> resultPath = paths[B];
        for(int i=0; i<resultPath.size(); i++){
            System.out.print(resultPath.get(i));
            if(i != resultPath.size()-1) System.out.print(" ");
        }
    }

    // Helper method to compare two paths lex order
    private static boolean isLexSmaller(List<Integer> path1, List<Integer> path2){
        int size1 = path1.size();
        int size2 = path2.size();
        int minSize = Math.min(size1, size2);
        for(int i=0; i<minSize; i++){
            if(path1.get(i) < path2.get(i)) return true;
            if(path1.get(i) > path2.get(i)) return false;
        }
        return size1 < size2;
    }
}