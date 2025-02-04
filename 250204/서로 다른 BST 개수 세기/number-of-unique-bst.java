import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[20];

        dp[0] = 1;
        dp[1] = 1;
        

        for(int i=2; i<=n; i++){
            dp[i] = num(i,dp);
        }
        
        System.out.print(dp[n]);
    }

    public static int num(int n,int[] dp){
        int count = 0;
        for(int i=0; i<n; i++){
            count += dp[i] * dp[n-i-1];
        }
        return count;
    }
}