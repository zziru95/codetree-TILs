import java.util.*;
import java.io.*;


public class Main {
    static class Pair{
        int r,c,t;
        public Pair(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    static int n,h,m;
    static int[][] direction = {{-1,0 }, {1,0}, {0,-1}, {0,1}};
    static int[][] arr;
    static boolean[][] visited;
    static ArrayList<Pair> peoples = new ArrayList<>();
    static Queue<Pair> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visited = new boolean[n][n];
        
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==2) {
                    peoples.add(new Pair(i,j,0));
                } else if(arr[i][j] ==3){
                    q.add(new Pair(i,j,0));
                    visited[i][j] = true;
                }
            }
        }

        bfs();

        
    }

    public static void bfs(){
        int[][] result = new int[n][n];
        
        while(!q.isEmpty()){
            Pair current = q.poll();
            int r = current.r;
            int c= current.c;
            int t = current.t;

            if(arr[r][c] == 2){
                result[r][c] = t;
            }

            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if( 0<= nr && nr<n && 0<= nc && nc< n && arr[nr][nc] !=1 && !visited[nr][nc] ){
                    visited[nr][nc] =true;
                    q.add(new Pair(nr,nc,t+1));
                }
            }

        }

        for(Pair people : peoples){
            if(result[people.r][people.c]== 0){
                result[people.r][people.c] = -1;
            }
        }

        printAnswer(result);
    }

    public static void printAnswer(int[][] answer){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<answer.length; i++){
            for(int j=0; j<answer[0].length;j++){
                sb.append(answer[i][j]);
                if(j<answer[0].length-1){
                    sb.append(" ");
                }
            }
            if(i<answer.length-1){
                sb.append(System.lineSeparator());
            }
        }
        System.out.print(sb);
    }
}