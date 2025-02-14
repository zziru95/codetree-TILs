import java.util.*;

public class Main {
    static int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][][] dp = new int[N + 1][3][3];

        // dp 배열을 -1이 아닌 0으로 초기화 (더 안정적)
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(dp[i][j], 0);
            }
        }

        // 초기 상태 설정
        dp[1][0][0] = 1; // 결석X, 지각X
        dp[1][1][0] = 1; // 지각1회, 결석X
        dp[1][0][1] = 1; // 결석1회, 지각X

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 3; j++) { // 지각 횟수
                for (int m = 0; m < 3; m++) { // 연속 결석 횟수
                    if (dp[i - 1][j][m] > 0) {
                        dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][m]) % MOD; // 출석
                    }
                    if (j > 0 && dp[i - 1][j - 1][m] > 0) {
                        dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j - 1][m]) % MOD; // 지각
                    }
                    if (m > 0 && dp[i - 1][j][m - 1] > 0) {
                        dp[i][j][m] = (dp[i][j][m] + dp[i - 1][j][m - 1]) % MOD; // 결석
                    }
                }
            }
        }

        int answer = 0;

        // 모든 경우의 수 합산
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                answer = (answer + dp[N][i][j]) % MOD;
            }
        }

        System.out.println(answer);
    }
}
