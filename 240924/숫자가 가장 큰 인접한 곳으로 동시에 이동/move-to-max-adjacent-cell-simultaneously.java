import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t;
    static int[][] arr;
    // 상, 하, 좌, 우 순으로 우선순위를 두기 위한 방향 배열
    static int[][] direction = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        n = Integer.parseInt(st.nextToken()); // 그리드 크기
        m = Integer.parseInt(st.nextToken()); // 초기 입자 수
        t = Integer.parseInt(st.nextToken()); // 시간 단계 수

        arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 입자 위치 카운트
        int[][] particleCount = new int[n][n];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 0-based 인덱스
            int c = Integer.parseInt(st.nextToken()) - 1;
            particleCount[r][c]++;
        }

        // 초기 큐 설정: 정확히 한 개의 입자가 있는 셀만 큐에 추가
        Queue<int[]> q = new LinkedList<>();
        int initialCount = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(particleCount[i][j] == 1) {
                    q.add(new int[] {i, j});
                    initialCount++;
                }
                // particleCount[i][j] >1 인 경우 모두 소멸
            }
        }

        // 시뮬레이션 시작
        for(int time = 0; time < t; time++) {
            int[][] temp = new int[n][n]; // 다음 단계 입자 배치
            // 현재 큐의 모든 입자 처리
            while(!q.isEmpty()) {
                int[] pos = q.poll();
                int r = pos[0];
                int c = pos[1];
                int currentValue = arr[r][c];

                // 이동할 셀 결정
                int targetR = r;
                int targetC = c;
                int maxValue = currentValue;

                for(int d = 0; d < 4; d++) {
                    int nr = r + direction[d][0];
                    int nc = c + direction[d][1];
                    if(nr >=0 && nr < n && nc >=0 && nc < n) {
                        if(arr[nr][nc] > maxValue) {
                            maxValue = arr[nr][nc];
                            targetR = nr;
                            targetC = nc;
                        }
                    }
                }

                // 이동 결정
                if(maxValue > currentValue) {
                    temp[targetR][targetC]++;
                    // 입자가 이동한 경우 출력 (디버깅 용)
                    // System.out.println("Particle moved from (" + r + "," + c + ") to (" + targetR + "," + targetC + ")");
                }
                else {
                    temp[r][c]++;
                    // 입자가 머무른 경우 출력 (디버깅 용)
                    // System.out.println("Particle stayed at (" + r + "," + c + ")");
                }
            }

            // 충돌 처리 및 다음 큐 설정
            Queue<int[]> nextQ = new LinkedList<>();
            int nextCount = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(temp[i][j] == 1) {
                        nextQ.add(new int[] {i, j});
                        nextCount++;
                    }
                    // temp[i][j] >1 인 경우는 모두 소멸되므로 큐에 추가하지 않음
                }
            }

            // 큐 업데이트 및 입자 수 업데이트
            q = nextQ;
            initialCount = nextCount;

            // 시간 단계별 입자 수 출력 (디버깅 용)
            // System.out.println("Time step: " + (time + 1) + ", Count: " + initialCount);

            // 최적화를 위해 더 이상 입자가 없으면 시뮬레이션 중단
            if(initialCount == 0) {
                break;
            }
        }

        // 최종 입자 수 출력
        System.out.print(initialCount);
    }
}