import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000007;  // 오버플로우 방지를 위한 MOD
    public static final int MAX_N = 1000;  // 최대 n 값
    
    // 변수 선언
    public static int n;
    public static long[] dp = new long[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.close();

        // 초기 조건 설정
        dp[0] = 1;
        dp[1] = 2;

        // 점화식 적용
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3) % MOD;
            for (int j = i - 3; j >= 0; j--) {
                dp[i] = (dp[i] + dp[j] * 2) % MOD;
            }
        }
        
        System.out.print(dp[n]);
    }
}
