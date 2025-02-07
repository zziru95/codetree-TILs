import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        dp = new int[N + 1][4];  // [층][1계단 이동 총 횟수 (최대 3번까지)]

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // ✅ 초기값 설정
        dp[1][1] = arr[1]; // 첫 번째 계단 오르면 무조건 1칸 이동 1번 사용
        
        if (N > 1) {
            dp[2][0] = arr[2];  // 1층을 건너뛰고 2층으로 바로 가는 경우 (1칸 이동 X)
            dp[2][2] = arr[1] + arr[2];  // 1층 -> 2층으로 이동 (1칸 이동 1번 사용)
        }

        // ✅ DP 테이블 채우기
        for (int i = 3; i <= N; i++) {
            for (int j = 0; j < 4; j++) {
                // 2칸 이동 (이전 j 값 유지)
                dp[i][j] = Math.max(dp[i][j], dp[i - 2][j] + arr[i]);

                // 1칸 이동 (총 3번까지만 가능)
                if (j > 0) {  // 1칸 이동을 할 경우, 이전 상태에서 j-1로 이동
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + arr[i]);
                }
            }
        }

        // ✅ N층에서 가능한 최대값 찾기
        int answer = 0;
        for (int j = 0; j < 4; j++) {
            answer = Math.max(answer, dp[N][j]);
        }

        System.out.println(answer);
    }
}
