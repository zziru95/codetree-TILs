import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] arr;
    static int maxCnt = -1;
    static int maxK = 0;
    static int answerK =0;
    static boolean[][] visited;
    static int[][] direction = { {-1,0 }, {1,0}, {0,1}, {0,-1} };

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr= new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxK=Math.max(arr[i][j], maxK);
            }
        }
        for(int k=1; k<maxK; k++){
            visited = new boolean[N][M];
            int tempCnt = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(arr[i][j]>k && !visited[i][j]) {
                        visited[i][j] = true;
                        tempCnt++;;
                        dfs(i,j,k);
                    }
                }
            }

            if(tempCnt>maxCnt){
                maxCnt = tempCnt;
                answerK = k;
            }
        }

        System.out.print(answerK+" "+maxCnt);
    }



    public static void dfs(int r, int c, int k){
        for(int d=0; d<4; d++){
            int nr = r+ direction[d][0];
            int nc = c+ direction[d][1];
            if(check(nr,nc) && !visited[nr][nc] && arr[nr][nc]>k){
                visited[nr][nc] = true;
                dfs(nr,nc,k);
            }
        }
    }

    public static boolean check(int r, int c){
        return 0<=r && r<N && 0<=c && c<M;
    }
}