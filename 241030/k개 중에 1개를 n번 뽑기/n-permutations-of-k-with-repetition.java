import java.util.*;
import java.io.*;

public class Main {
    static int K, N;
    static int[] comb;
    static StringBuilder sb = new StringBuilder(); // static으로 선언하여 한 번에 출력

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        comb = new int[N];

        choose(0);
        System.out.print(sb); // 한 번에 출력
    }

    public static void choose(int cnt) {
        if (cnt == N) {
            for (int i = 0; i < N; i++) {
                sb.append(comb[i]).append(" ");
            }
            sb.setLength(sb.length() - 1); // 마지막 공백 제거
            sb.append("\n"); // 한 줄 끝난 후 줄바꿈 추가
            return;
        }

        for (int i = 1; i <= K; i++) {
            comb[cnt] = i;
            choose(cnt + 1);
        }
    }
}