import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] arr;
    static final int INF = Integer.MIN_VALUE;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        dp = new int[N + 1][K + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = INF;

        // DP 수행
        for(int i =1 ; i<=N ; i++){
            int num = arr[i];
            if(num>=0){
                for(int j=0; j<=K; j++){
                    dp[i][j] = Math.max(dp[i-1][j] + num, dp[i][j]);
                    ans = Math.max(ans,dp[i][j]);
                }
            } else{
                for(int j=1; j<=K; j++){
                    dp[i][j] = Math.max(dp[i-1][j-1] + num, dp[i][j]);
                    ans = Math.max(ans,dp[i][j]);
                }
            }
        }


        System.out.println(ans);
    }
}
