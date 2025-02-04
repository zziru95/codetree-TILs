import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        int[] b = {1,2,5};
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int a : b){
            for(int i=3; i<=n; i++){
                dp[i] = dp[i-1] + dp[i-2];
                if(i>=5) dp[i] += dp[i-5];
                dp[i] %= 10007;  
            }
        }
        
        System.out.print(dp[n]);

    }
}