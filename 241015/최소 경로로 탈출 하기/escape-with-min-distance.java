import java.util.*;
import java.io.*;


public class Main {
    static int n,m;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} };
    static int[][] arr;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(bfs());
    }


    public static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0,0,0});
        visited[0][0] = true;
        int minV = -1;
        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int t = current[2];
            if(r==n-1 && c==m-1) {
                minV = t;
                break;
            }
            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(0<=nr && nr< n && 0<= nc && nc< m && !visited[nr][nc] && arr[nr][nc]==1){
                    visited[nr][nc] = true;
                    q.add(new int[] {nr,nc,t+1});
                }
            }
        }

        return minV;
    }
}