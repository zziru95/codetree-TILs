import java.util.*;
import java.io.*;

public class Main {
    static long m;
    static long a, b;
    static long maxT = 0;
    static long minT = Long.MAX_VALUE; // Integer.MAX_VALUE -> Long.MAX_VALUE로 변경

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 입력: m
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Long.parseLong(st.nextToken()); // m을 long으로 입력 받기

        // 두 번째 입력: a, b
        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken()); // a를 long으로 입력 받기
        b = Long.parseLong(st.nextToken()); // b를 long으로 입력 받기

        // a에서 b까지 모든 숫자에 대해 baseball 함수 호출
        for (long i = a; i <= b; i++) {
            baseball(i);
        }

        // 결과 출력
        System.out.print(minT + " " + maxT);
    }

    public static void baseball(long num) {
        long left = 1;
        long right = m;
        long cnt = 1; // 카운트도 long으로 처리

        // 이진 탐색 수행
        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid == num) break;

            cnt++;
            if (mid > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 최대, 최소 업데이트
        maxT = Math.max(maxT, cnt);
        minT = Math.min(minT, cnt);
    }
}