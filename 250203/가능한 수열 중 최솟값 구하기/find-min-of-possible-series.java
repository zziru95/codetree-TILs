import java.util.Scanner;

public class Main {
    
    static int n;  // 수열 길이
    static int[] sequence;  // 수열 저장 배열

    // 중복된 연속 부분 수열이 있는지 검사
    static boolean isValid(int length) {
        for (int m = 1; m <= length / 2; m++) {
            boolean isDuplicate = true;
            for (int i = 0; i < m; i++) {
                if (sequence[length - 2 * m + i] != sequence[length - m + i]) {
                    isDuplicate = false;
                    break;
                }
            }
            if (isDuplicate) return false;  // 중복된 부분 수열이 존재하면 false 반환
        }
        return true;
    }

    // 백트래킹을 이용하여 가능한 수열을 찾음
    static boolean findSequence(int depth) {
        if (depth == n) {
            for (int num : sequence) System.out.print(num);  // 배열 출력
            System.out.println();
            return true;  // 가장 앞선 사전순 결과이므로 종료
        }

        // 사전순으로 가장 앞선 수열을 찾기 위해 4, 5, 6 순으로 탐색
        for (int num : new int[]{4, 5, 6}) {
            sequence[depth] = num;  // 현재 위치에 숫자 추가
            if (isValid(depth + 1)) {  // 유효한 수열인지 검사
                if (findSequence(depth + 1)) {
                    return true;  // 정답 찾으면 종료
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();  // 수열 길이 입력 받기
        sc.close();

        sequence = new int[n];  // 길이가 n인 배열 생성
        findSequence(0);  // 백트래킹 시작
    }
}
