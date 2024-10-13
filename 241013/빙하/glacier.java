import java.util.*;
import java.io.*;


public class Main {
    static int N,M;
    static int[][] direction = { {-1,0},{1,0},{0,1},{0,-1} };
    static int[][] arr;
    static int[] answer = {0,0};
    static boolean nomore1 = false;
    
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(!nomore1){
            bfs();
        }

        System.out.print(answer[0]+ " "+ answer[1]);

    }


    public static void bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[] {0,0});
        visited[0][0] = true;
        ArrayList<int[]> solving = new ArrayList<>();
        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c= current[1];

            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(valid(nr,nc)&& !visited[nr][nc]){
                    visited[nr][nc] = true;
                    if(arr[nr][nc] == 0){
                        q.add(new int[] {nr,nc});
                    } else if(arr[nr][nc] == 1){
                        solving.add(new int[] {nr,nc});
                    }
                }
            }
        }

        if(solving.isEmpty()) {
            nomore1 = true;
            return;
        } else{
            answer[0]++;
            answer[1] = solving.size();
            for(int[] temp : solving){
                arr[temp[0]][temp[1]] = 0;
            }
        }

        
    }

    public static boolean valid (int r, int c){
        return 0<= r && r<N && 0<= c && c<M;
    }
}