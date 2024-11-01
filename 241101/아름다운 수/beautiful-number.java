import java.util.*;
import java.io.*;

public class Main {
    static int n, count;
    static char[] comb;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        count = 0;
        comb = new char[n];
        choose(0);
        System.out.print(count);
    }

    public static void choose(int cnt) {
        if (cnt == n) {
            if (isBeauty()) count++;
            return;
        }

        for (int i = 1; i < 5; i++) {
            comb[cnt] = Integer.toString(i).charAt(0);
            choose(cnt + 1);
        }
    }

    public static boolean isBeauty() {
        char before = comb[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            char now = comb[i];
            if (now == before) {
                count++; // 동일한 숫자가 연속되면 count 증가
            } else {
                int digit = before - '0';
                if (before != '1' && count % digit != 0) return false; // 배수 조건 확인
                before = now; // 새로운 그룹 시작
                count = 1;
            }
        }

        // 마지막 그룹 확인
        int digit = before - '0';
        if (before != '1' && count % digit != 0) return false;

        return true;
    }
}