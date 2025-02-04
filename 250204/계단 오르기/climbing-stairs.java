import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[1001];
        dp[2] = 1;
        dp[3] = 1;

        for(int i=4; i<=n; i++){
            dp[i] = dp[i-2] + dp[i-3];
        }

        System.out.print(dp[n]);
    }
}