import java.util.*;
import java.io.*;

public class Main {

    static int[][] direction = { {1,0} ,{-1,0}, {0,1}, {0,-1}};
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int maxBlock =0;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new boolean[n][n];
        int cnt =0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    visited[i][j] = true;
                    int temp = dfs(i,j,arr[i][j]);
                    if(temp>=4){
                        cnt++;
                    }
                    maxBlock = Math.max(temp,maxBlock);
                }
            }
        }

        System.out.print(cnt+" "+maxBlock);
    }

    public static int dfs(int r, int c, int k){
        int cnt =1;
        for(int d=0; d<4; d++){
            int nr = r+ direction[d][0];
            int nc = c+ direction[d][1];
            if(0<= nr && nr <n && 0<= nc && nc< n && !visited[nr][nc] &&arr[nr][nc] ==k){
                visited[nr][nc] = true;
                cnt += dfs(nr,nc,k);
            }
        }

        return cnt;
    }
}