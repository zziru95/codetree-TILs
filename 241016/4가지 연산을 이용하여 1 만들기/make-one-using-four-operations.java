import java.util.*;
import java.io.*;

public class Main {
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] memo = new int[N + 1];
        
        // 초기값 설정
        memo[1] = 0;

        for (int i = 2; i <= N; i++) {
            // 1을 뺀 경우
            memo[i] = memo[i - 1] + 1;

            // 2로 나누어 떨어지는 경우
            if (i % 2 == 0) {
                memo[i] = Math.min(memo[i], memo[i / 2] + 1);
            }

            // 3으로 나누어 떨어지는 경우
            if (i % 3 == 0) {
                memo[i] = Math.min(memo[i], memo[i / 3] + 1);
            }
        }

        System.out.print(memo[N]);
    }
}