import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] dp;
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
        dp = new boolean[target + 1];
        dp[0] = true;  // ✅ 초기 상태 설정

        // 1차원 DP 업데이트
        for (int num : arr) {
            for (int j = target; j >= num; j--) {  // ✅ 역방향 순회 (큰 값부터)
                dp[j] = dp[j] || dp[j - num];  // ✅ num을 추가할 수 있는 경우 갱신
            }
        }

        // 최대 i 찾기 (A와 B의 합이 같도록)
        int maxEqualSum = 0;
        for (int i = target; i >= 0; i--) {
            if (dp[i]) {  // ✅ i 값이 가능한 가장 큰 경우 찾기
                maxEqualSum = i;
                break;
            }
        }

        // 정답 출력 (A와 B의 합이 동일하므로 *2)
        System.out.println(maxEqualSum);
    }
}
