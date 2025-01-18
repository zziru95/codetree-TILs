import java.util.*;
import java.io.*;
public class Main {

    static int[] parent,depth;
    static int n;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        parent = new int[n+1];
        depth = new int[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
            parent[i] = -1;
            depth[i] = 0;
        }

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[p].add(c);
            parent[c] = p;
        }

        int root = -1;
        for(int i=1; i<=n; i++){
            if(parent[i]==-1) root = i;
        }
        
        dfs(root,0);

        st = new StringTokenizer(br.readLine());
        int t1 = Integer.parseInt(st.nextToken());
        int t2 = Integer.parseInt(st.nextToken());
        System.out.print(solve(t1,t2));
    }

    public static void dfs(int a, int b){
        depth[a] = b;
        for(int c : graph[a]){
            dfs(c,b+1);
        }
    }

    public static int solve(int a, int b){
        int t1 = a;
        int t2 = b;
        while(depth[t1] != depth[t2]){
            if(depth[t1]>depth[t2]){
                t1 = parent[t1];
            } else{
                t2 = parent[t2];
            }
        }

        while(t1!=t2){
            t1 = parent[t1];
            t2 = parent[t2];
        }
        return t1;
    }
}