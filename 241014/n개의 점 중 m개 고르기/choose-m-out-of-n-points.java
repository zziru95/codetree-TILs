import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] dots;
    static int minD = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dots = new int[n][2]; // 점 좌표 배열

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }

        choose(0, 0, 0); // 백트래킹 시작
        System.out.print(minD);
    }

    // 점 선택을 위한 백트래킹 함수
    public static void choose(int start, int cnt, int maxDistance) {
        if (cnt == m) {
            // m개의 점을 모두 선택한 경우 최소 거리 갱신
            minD = Math.min(minD, maxDistance);
            return;
        }

        for (int i = start; i < n; i++) {
            // 새로 선택할 점을 dots[i]로 설정
            int newMaxDistance = maxDistance;

            // 선택된 점들과의 최대 거리를 계산하여 업데이트
            for (int j = 0; j < cnt; j++) {
                int distance = calculateDistance(dots[i], dots[j]);
                newMaxDistance = Math.max(newMaxDistance, distance);
            }

            // 다음 점을 선택 (새로운 최대 거리와 함께)
            choose(i + 1, cnt + 1, newMaxDistance);
        }
    }

    // 두 점 사이의 거리 제곱 계산 함수
    public static int calculateDistance(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return dx * dx + dy * dy;
    }
}