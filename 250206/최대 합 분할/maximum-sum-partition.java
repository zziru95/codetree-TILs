import java.io.*;
import java.util.*;

public class Main {
    static final int OFFSET = 100000; // sumA - sumB의 범위를 보정하기 위한 오프셋
    static final int MN = Integer.MIN_VALUE; // 초기 최소값 설정
    static int n, total;
    static int[] arr;
    static int[][] dp; // DP 배열: dp[i][j] → i번째까지 고려했을 때 sumA - sumB = j의 최대 sumA 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }

        dp = new int[2][2 * total + 1]; // 2D DP 대신 2줄만 유지하여 메모리 절약
        for (int i = 0; i < 2; i++) Arrays.fill(dp[i], MN);
        dp[0][total] = 0; // sumA - sumB = 0 (초기 상태)

        // DP 진행
        for (int i = 1; i <= n; i++) {
            int num = arr[i];
            for (int diff = -total; diff <= total; diff++) {
                if (dp[(i - 1) % 2][diff + total] == MN) continue; // 이전 상태가 불가능하면 무시

                // 1. A에 추가 (sumA + num)
                dp[i % 2][diff + num + total] = Math.max(
                    dp[i % 2][diff + num + total],
                    dp[(i - 1) % 2][diff + total] + num
                );

                // 2. B에 추가 (sumB + num)
                dp[i % 2][diff - num + total] = Math.max(
                    dp[i % 2][diff - num + total],
                    dp[(i - 1) % 2][diff + total]
                );

                // 3. C에 추가 (아무 그룹에도 넣지 않음)
                dp[i % 2][diff + total] = Math.max(
                    dp[i % 2][diff + total],
                    dp[(i - 1) % 2][diff + total]
                );
            }
        }

        // 최적 해 찾기 (sumA - sumB = 0이 되는 경우 중 최대 sumA)
        int maxEqualSum = dp[n % 2][total] == MN ? 0 : dp[n % 2][total];
        System.out.println(maxEqualSum);
    }
}
