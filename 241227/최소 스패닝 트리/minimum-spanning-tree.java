import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int from,to,w;
        public Node(int from, int to, int w){
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o){
            return this.w-o.w;
        }

    }
    static int n,m;
    static List<Node> list = new ArrayList<>();
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.add(new Node(from, to,w));
        }

        int answer = kruskal();
        System.out.print(answer);
    }


    public static int find(int x){
        if(parent[x]==x) return x;

        int root = find(parent[x]);
        parent[x] = root;
        return root;
    }

    public static boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX==rootY) return false;
        parent[Math.max(rootX,rootY)] = Math.min(rootX,rootY);
        return true;
    }

    public static int kruskal(){
        int total = 0;
        int count = 0;
        Collections.sort(list);
        for(Node node : list){
            if(union(node.from, node.to)){
                total += node.w;
                count++;
                if(count == n-1){
                    break;
                }
            }


        }
        return total;
    }
}