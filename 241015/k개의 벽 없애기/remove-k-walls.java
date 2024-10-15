import java.util.*;
import java.io.*;

public class Main {
    static int n,k;
    static int wallCnt = 0;
    static int[][] direction = { {-1,0},{1,0},{0,1},{0,-1} };
    static boolean[][] visited;
    static int[][] arr;
    static ArrayList<int[]> walls = new ArrayList<>();
    static int[][] comb;
    static int minD = Integer.MAX_VALUE;
    static int[] start = new int[2];
    static int[] end = new int[2];

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new boolean[n][n];
        comb = new int[k][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1) {
                    walls.add(new int[] {i,j});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken())-1;
        start[1] = Integer.parseInt(st.nextToken())-1;
        
        st = new StringTokenizer(br.readLine());
        end[0] = Integer.parseInt(st.nextToken())-1;
        end[1] = Integer.parseInt(st.nextToken())-1;
        choose(0,0);
        System.out.print((minD == Integer.MAX_VALUE) ? -1 : minD);
    }


    public static void choose(int start, int cnt){
        if(cnt ==k){
            for(int[] wall : comb){
                arr[wall[0]][wall[1]] = 0;
            }
            int temp = bfs();
            if(temp != -1){
                minD = temp;
            }
            
            for(int[] wall : comb){
                arr[wall[0]][wall[1]] = 1;
            }
            return;
        }

        for(int i=start;i<walls.size();i++){
            comb[cnt] = walls.get(i);
            choose(i+1,cnt+1);
        }
    }


    public static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        resetVisited();
        q.add(new int[] {start[0],start[1],0});
        visited[start[0]][start[1]] = true;
        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int t = current[2];
            if(r==end[0] && c==end[1]){
                return t;
            }

            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(0<=nr && nr< n && 0<= nc && nc<n &&!visited[nr][nc]&& arr[nr][nc]==0){
                    q.add(new int[] {nr,nc,t+1});
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }



    public static void resetVisited(){
        for(int i=0; i<n; i++){
            Arrays.fill(visited[i],false);
        }
    }
}