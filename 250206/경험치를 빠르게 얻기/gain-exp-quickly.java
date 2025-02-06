import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[][] arr;
    static int[] dp;
    static int INF = Integer.MAX_VALUE;
    static int answer = INF;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][2];
        int sum = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            sum += arr[i][0];
        }

        if(sum<m){
            System.out.print(-1);
            return;
        }

        dp = new int[sum+1];
        Arrays.fill(dp,INF);
        dp[0] = 0;

        for(int[] quest : arr){
            int exp = quest[0];
            int time = quest[1];
            
            for(int i=sum; i>=exp; i--){
                if(dp[i-exp] == INF) continue;
                dp[i] = Math.min(dp[i] , dp[i-exp] + time);
                if(i>=m){
                    answer = Math.min(answer,dp[i]);
                }
            }
        }

        System.out.println(answer);


    }
}

