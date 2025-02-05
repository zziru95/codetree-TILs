import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[][] dp;
    static int totalSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        totalSum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            totalSum += arr[i];
        }

        int target = totalSum / 2;
        dp = new boolean[target + 1][target + 1];
        dp[0][0] = true;  // ✅ 초기 상태 설정

        // **DP 갱신을 위한 새로운 DP 배열 생성**
        for (int num : arr) {
            boolean[][] newDp = new boolean[target + 1][target + 1];

            for (int i = 0; i <= target; i++) {
                for (int j = 0; j <= target; j++) {
                    if (dp[i][j]) {  // 현재 상태가 가능하면 갱신
                        newDp[i][j] = true;  // 기존 상태 유지
                        if (i + num <= target) newDp[i + num][j] = true;  // A 그룹에 추가
                        if (j + num <= target) newDp[i][j + num] = true;  // B 그룹에 추가
                    }
                }
            }

            dp = newDp;  // ✅ 새로운 DP 배열로 교체
        }

        // ✅ 최적 해 찾기 (A와 B의 합이 같도록)
        int maxEqualSum = 0;
        for (int i = target; i >= 0; i--) {
            if (dp[i][i]) {  // ✅ A와 B가 같은 합이 되는 가장 큰 값 찾기
                maxEqualSum = i;
                break;
            }
        }

        // 정답 출력 (A와 B의 합이 동일하므로 *2)
        System.out.println(maxEqualSum );
    }
}
