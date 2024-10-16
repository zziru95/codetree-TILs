import java.util.*;
import java.io.*;

public class Main {
    static long m;          // m은 최대 10^18이므로 long 사용
    static int a, b;        // a, b는 최대 10^5 범위 내 차이를 가지므로 int 사용
    static int maxT = 0;
    static int minT = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 입력: m
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Long.parseLong(st.nextToken());  // m을 long으로 입력 받기

        // 두 번째 입력: a, b
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());  // a를 int로 입력 받기
        b = Integer.parseInt(st.nextToken());  // b를 int로 입력 받기

        // a에서 b까지 모든 숫자에 대해 baseball 함수 호출
        for (int i = a; i <= b; i++) {
            baseball(i);
        }

        // 결과 출력
        System.out.print(minT + " " + maxT);
    }

    public static void baseball(int num) {
        long left = 1;         // 이진 탐색에 필요한 변수는 long으로 처리
        long right = m;
        int cnt = 1;           // 카운트는 범위가 작으므로 int로 처리

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