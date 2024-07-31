import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];

        // 입력 받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int maxGold = 0;

        // K를 0부터 2n-1까지 순회
        for (int k = 0; k <= 2 * n - 1; k++) {
            int cost = k * k + (k + 1) * (k + 1);

            // 그리드를 순회하며 각 점을 중심으로 마름모 모양으로 금 채굴
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int gold = getGold(grid, i, j, k, n);
                    if (gold * m >= cost) {
                        maxGold = Math.max(maxGold, gold);
                    }
                }
            }
        }

        System.out.println(maxGold);
        sc.close();
    }

    // 주어진 (x, y) 위치에서 k 크기의 마름모 모양으로 채굴 가능한 금의 개수 계산
    private static int getGold(int[][] grid, int x, int y, int k, int n) {
        int gold = 0;
        for (int i = -k; i <= k; i++) {
            for (int j = -k; j <= k; j++) {
                if (Math.abs(i) + Math.abs(j) <= k) {
                    int nx = x + i;
                    int ny = y + j;
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        gold += grid[nx][ny];
                    }
                }
            }
        }
        return gold;
    }
}