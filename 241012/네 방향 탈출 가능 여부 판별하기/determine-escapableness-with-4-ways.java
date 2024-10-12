import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static int[][] direction = { {-1,0},{1,0},{0,1},{0,-1} };
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0,0});
        visited[0][0] = true;
        int answer = 0;
        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            if(r==n-1 && c==m-1) {
                answer =1;
                break;
            }
            for(int d=0; d<4; d++){
                int nr = r+ direction[d][0];
                int nc = c+ direction[d][1];
                if(0<=nr && nr<n && 0<= nc && nc<m && !visited[nr][nc] && map[nr][nc]==1){
                    visited[nr][nc] = true;
                    q.add(new int[] {nr,nc});
                }
            }
        }

        System.out.print(answer);

    }
}