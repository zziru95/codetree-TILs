import java.util.*;
import java.io.*;

public class Main {

    static int n,k,u,d;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} };
    static int[][] arr;
    static boolean[][] visited;
    static int[][] comb;
    static int maxGo =0;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visited =new boolean[n][n];
        comb = new int[k][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        choose(0,0);
        System.out.print(maxGo);

    }


    public static void choose(int cnt, int start){
        if(cnt ==k){
            bfs();
            return;
        }

        for(int i=start; i<n*n; i++){
            int r = i/n;
            int c = i%n;
            comb[cnt][0]= r;
            comb[cnt][1] = c;
            choose(cnt+1, i+1);
        }   
    }

    public static void bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 0;
        resetVisited();
        for(int[] temp : comb){
            q.add(new int[] {temp[0],temp[1]});
            visited[temp[0]][temp[1]] = true;
            cnt++;
        }

        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            for(int t=0; t<4; t++){
                int nr = r+ direction[t][0];
                int nc = c+ direction[t][1];
                if(0<=nr && nr<n && 0<=nc && nc<n && !visited[nr][nc]){
                    int diff = Math.abs(arr[r][c]-arr[nr][nc]);
                    if(u<=diff && diff <=d){
                        q.add(new int[]{nr,nc});
                        visited[nr][nc] = true;
                        cnt++;
                    }
                }
            }
        }

        maxGo = Math.max(maxGo,cnt);

    }

    public static void resetVisited(){
        for(int i=0; i<n; i++){
            Arrays.fill(visited[i],false);
        }
    }
}