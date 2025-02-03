import java.util.*;
import java.io.*;
public class Main {

    static int n, sr, sc;
    static int answer = 0;
    static int[][] graph, dGraph;
    static int[][] direction = {{0,0}, {-1,0}, {-1,1},{0,1}, {1,1}, {1,0}, {1,-1},{0,-1},{-1,-1}};
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        dGraph = new int[n][n];
        fill(graph,st,br);
        fill(dGraph,st,br);
        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;

        dfs(sr,sc,0);
        System.out.print(answer);

    }

    public static void fill(int[][] arr, StringTokenizer st, BufferedReader br) throws IOException{
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static boolean valid(int r, int c){
        return 0<=r && r<n && 0<=c && c<n;
    }

    public static void dfs(int r, int c, int t){
        answer = Math.max(answer, t);

        int d = dGraph[r][c];
        int value = graph[r][c];
        int nr = r + direction[d][0];
        int nc = c + direction[d][1];
        while(valid(nr,nc)){
            
            if(graph[nr][nc] > value) dfs(nr,nc,t+1);
            nr += direction[d][0];
            nc += direction[d][1];
        }
    }
}