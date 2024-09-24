import java.util.*;
import java.io.*;

public class Main {
    // 방향: 0=상, 1=하, 2=우, 3=좌
    static final int[][] DIRECTION = { {-1,0}, {1,0}, {0,1}, {0,-1} };
    // '/' 반사경 방향 전환: 0→2, 1→3, 2→0, 3→1
    static final int[] BAR1 = {2, 3, 0, 1};
    // '\' 반사경 방향 전환: 0→3, 1→2, 2→1, 3→0
    static final int[] BAR2 = {3, 2, 1, 0};
    static int[][] frame;
    static int n;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        n = Integer.parseInt(st.nextToken());
        frame = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                frame[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxValue = Integer.MIN_VALUE;

        // 모든 시작점에서 시뮬레이션 실행
        for(int i = 0; i < n; i++) {
            play(i, 0, 3);      // 좌측 가장자리, 좌 방향
            play(i, n-1, 2);    // 우측 가장자리, 우 방향
            play(0, i, 1);      // 상단 가장자리, 하 방향
            play(n-1, i, 0);    // 하단 가장자리, 상 방향
        }

        // 결과 출력
        System.out.print(maxValue);
    }

    public static void play(int r, int c, int d){
        int time = 0;
        int stepLimit = n * n * 4; // 무한 루프 방지를 위한 제한

        while(true){
            if(time <= stepLimit) return;
            r += DIRECTION[d][0];
            c += DIRECTION[d][1];
            time++;

            // 프레임을 벗어나면 종료
            if(r < 0 || r >= n || c < 0 || c >= n) {
                break;
            }

            // 현재 위치의 셀이 1인 경우 '/' 반사경
            if(frame[r][c] == 1) {
                d = BAR1[d];
            }
            // 현재 위치의 셀이 2인 경우 '\' 반사경
            else if(frame[r][c] == 2) {
                d = BAR2[d];
            }
        }

        // 최대 시간 업데이트
        if(time > maxValue){
            maxValue = time;
        }
    }
}