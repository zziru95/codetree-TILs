import java.io.*;
import java.util.*;

public class Main {
    //좌상,우상,우하,좌하
    static int[][] direction = { {-1,-1}, {-1,1}, {1,1}, {1,-1} };
    static int maxValue =0;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] frame = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                frame[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i,j,i,j,0,n,0,0,frame);
            }
        }

        System.out.println(maxValue);



    }


    public static void dfs(int sr, int sc,int r, int c, int d, int n, int depth, int score, int[][] frame){
        if(depth>3) return;

        if(depth==3 && r==sr && c==sc ) maxValue=Math.max(score,maxValue);

        int nr = r + direction[d][0];
        int nc = c + direction[d][1];

        if(checkInder(nr,nc,n)){
            dfs(sr,sc,nr,nc,d,n,depth,score+frame[nr][nc],frame);
        }

        d = ( d + 1 ) % 4;
        depth++;
        nr = r + direction[d][0];
        nc = c + direction[d][1];

        if(checkInder(nr,nc,n)){
            dfs(sr,sc,nr,nc,d,n,depth,score+frame[nr][nc],frame);
        }




    }

    public static boolean checkInder(int r, int c, int n){
        return 0 <= r && r < n && 0 <= c && c < n;
    }

}