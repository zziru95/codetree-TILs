import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] red, blue;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        red = new int[2*N+1];
        blue = new int[2*N+1];
        dp = new int[N+1][N+1];

        for(int i=1; i<=2*N; i++){
            st = new StringTokenizer(br.readLine());
            red[i] = Integer.parseInt(st.nextToken());
            blue[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<=N; i++){
            for(int j=0; j<=N; j++){
                if(i>0){
                    dp[i][j] = Math.max(dp[i-1][j] + red[i+j], dp[i][j]);    
                }
                if(j>0){
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1]+ blue[i+j]);
                }
                
            }

        }

        int answer = 0;
        for(int i=0; i<=N; i++){
            answer = Math.max(dp[N][i],answer);
        }
        System.out.print(answer);

    }
}