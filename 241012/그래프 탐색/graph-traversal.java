import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static boolean[] visited;
    static int[][] arr;
    static int cnt;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        arr = new int[N+1][N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[s][e] = 1;
            arr[e][s] = 1;
        }
        visited[1] = true;
        dfs(1);
        System.out.print(cnt);
    }

    public static void dfs(int s){
        for(int i=1; i<=N; i++){
            if(arr[s][i] ==1 && !visited[i]){
                visited[i]=true;
                cnt++;
                dfs(i);
            }
        }
    }
}