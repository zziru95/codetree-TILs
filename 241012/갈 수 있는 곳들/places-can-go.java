import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} };
    static int[][] arr;
    static boolean[][] visited;
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new boolean[n][n];
        q = new ArrayDeque<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
               arr[i][j] = Integer.parseInt(st.nextToken()); 
            }
        }
        int cnt = 0;
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            q.add(new int[] {r,c});
            visited[r][c] = true;
            cnt++;
        }

        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(0<=nr && nr< n && 0<=nc && nc<n && !visited[nr][nc] && arr[nr][nc] ==0){
                    cnt++;
                    visited[nr][nc] = true;
                    q.add(new int[] {nr,nc});
                }
            }
        }

        System.out.print(cnt);
    }
}