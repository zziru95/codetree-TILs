import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] dp;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new int[N+1][3];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[1][1] = arr[1];
        
        for(int i=2; i<=N; i++){
            for(int j=0; j<3; j++){
                dp[i][j] = Math.max(dp[i-2][j] + arr[i],dp[i][j]);
                
                if(j!=2){
                    dp[i][j+1] = Math.max(dp[i-1][j] + arr[i],dp[i][j+1]);
                }
            }
        }
        int answer = Math.max(dp[N][0],dp[N][1]);
        answer = Math.max(answer,dp[N][2]);
        System.out.print(answer);
                                       
    }
}