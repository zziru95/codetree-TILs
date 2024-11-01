import java.util.*;
import java.io.*;

public class Main {
    static int n, count;
    static char[] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        count = 0;
        n = Integer.parseInt(st.nextToken());
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
        char before = 'z';
        int idx = -1;
        int count = 1;
        
        for (int i = 0; i < n; i++) {
            char now = comb[i];
            if (before != now) { // 새로운 숫자 그룹이 시작됨
                if (before != 'z') { // 첫 번째 문자 그룹이 아니면
                    if (count % (before - '0') != 0) return false; // 배수 확인
                }
                before = now;
                idx = i; // 현재 그룹의 시작 인덱스 업데이트
                count = 1;
            }
        }

        // 마지막 그룹에 대한 확인
        if (before != 1 && count % (before - '0') != 0) return false;


        return true;
    }
}