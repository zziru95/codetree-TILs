import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] grid;
    static List<Pair> bombs = new ArrayList<>();
    static boolean[][] destroyed;
    static int maxDestroyed = 0;

    static class Pair {
        int r, c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 폭탄 종류별 영향 범위 정의
    static int[][] bombType1 = {
        {0, 0}, // 현재 위치
        {-1, 0}, // 위
        {1, 0},  // 아래
        {2, 0}, // 왼쪽
        {-2, 0}   // 오른쪽
    };

    static int[][] bombType2 = {
        {0, 0}, // 현재 위치
        {-1, 0}, // 좌상
        {1, 0},  // 우상
        {0, -1},  // 좌하
        {0, 1}    // 우하
    };

    static int[][] bombType3 = {
        {-1, -1},  {-1, 1},
          {0, 0},  
        {1, -1},   {1, 1}
    };

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().trim().split("\\s+");
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(tokens[j]);
                if (grid[i][j] == 1) {
                    bombs.add(new Pair(i, j));
                }
            }
        }

        destroyed = new boolean[N][N];
        // DFS 시작
        dfs(0, 0);

        // 결과 출력
        System.out.println(maxDestroyed);
    }

    static void dfs(int idx, int totalDestroyed) {
        if (idx == bombs.size()) {
            // 최대 초토화 영역 갱신
            if (totalDestroyed > maxDestroyed) {
                maxDestroyed = totalDestroyed;
            }
            return;
        }

        int r = bombs.get(idx).r;
        int c = bombs.get(idx).c;

        // 3가지 폭탄 종류에 대해 시도
        for (int bombType = 1; bombType <= 3; bombType++) {
            int[][] effect;
            if (bombType == 1) {
                effect = bombType1;
            } else if (bombType == 2) {
                effect = bombType2;
            } else {
                effect = bombType3;
            }

            List<Pair> newlyDestroyed = new ArrayList<>();

            // 폭탄 효과 적용
            for (int[] dir : effect) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if (!destroyed[nr][nc]) {
                        destroyed[nr][nc] = true;
                        newlyDestroyed.add(new Pair(nr, nc));
                    }
                }
            }

            // 다음 폭탄으로 재귀 호출
            dfs(idx + 1, totalDestroyed + newlyDestroyed.size());

            // 상태 복원 (백트래킹)
            for (Pair p : newlyDestroyed) {
                destroyed[p.r][p.c] = false;
            }
        }
    }
}