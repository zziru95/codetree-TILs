import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N+1][42];


        dp[1][arr[1] + 20] += 1; 
        if (arr[1] != 0) dp[1][20 - arr[1]] += 1;
        
        for(int i=2; i<=N; i++){
            for(int j=0; j<=41; j++){
                if(dp[i-1][j] != 0){
                    int prev = j-20;
                    int plus = prev+ arr[i];
                    int minus = prev - arr[i];
                    
                    if(check(plus)){
                        dp[i][plus+20] += dp[i-1][j];
                    }
                    if(check(minus)){
                        dp[i][minus+20] += dp[i-1][j];
                    }
                }
            }
        }

        System.out.print(dp[N][M+20]);
    }

    public static boolean check(int a){
        return -20<=a && a<=20;
    }
}