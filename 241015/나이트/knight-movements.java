import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] direction = { {-1,-2},{-2,-1},{-2,1},{-1,2},{1,-2},{2,-1},{2,1},{1,2} };
    static int[][] arr;
    static boolean[][] visited;
    static int[] start = new int[2];
    static int[] end = new int[2];
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new boolean[n][n];
        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());
        end[0] = Integer.parseInt(st.nextToken());
        end[1] = Integer.parseInt(st.nextToken());
        System.out.print(bfs());
    }


    public static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {start[0],start[1],0});
        visited[start[0]][start[1]] = true;
        int answer = -1;
        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int t = current[2];

            if(r==end[0] && c == end[1]){
                answer = t;
                break;
            }

            for(int d=0; d<8; d++){
                int nr = r+direction[d][0];
                int nc = c+direction[d][1];
                if(0<=nr && nr< n && 0<=nc && nc<n &&!visited[nr][nc]){
                    visited[nr][nc] = true;
                    q.add(new int[] {nr,nc,t+1});
                }
            }
        }

        return answer;
    }
}