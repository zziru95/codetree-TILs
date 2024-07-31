import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] prefixSum = new int[n][m];
        prefixSum[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            prefixSum[i][0] = matrix[i][0] + prefixSum[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            prefixSum[0][j] = matrix[0][j] + prefixSum[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                prefixSum[i][j] = matrix[i][j] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
            }
        }


        int maxSum = Integer.MIN_VALUE;

        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < m; c1++) {
                for (int r2 = r1; r2 < n; r2++) {
                    for (int c2 = c1; c2 < m; c2++) {
                        int rect1Sum = prefixSum(prefixSum, r1, c1, r2, c2);

                        // 두 번째 직사각형과 겹치지 않게 선택
                        for (int r3 = 0; r3 < n; r3++) {
                            for (int c3 = 0; c3 < m; c3++) {
                                for (int r4 = r3; r4 < n; r4++) {
                                    for (int c4 = c3; c4 < m; c4++) {
                                        if (isNonOverlapping(r1, c1, r2, c2, r3, c3, r4, c4)) {
                                            int rect2Sum = prefixSum(prefixSum, r3, c3, r4, c4);
                                            maxSum = Math.max(maxSum, rect1Sum + rect2Sum);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
    

    public static int prefixSum(int[][] prefixSum, int sn, int sc, int en, int ec) {
        int total = prefixSum[en][ec];

        if (sn > 0) {
            total -= prefixSum[sn - 1][ec];
        }
        if (sc > 0) {
            total -= prefixSum[en][sc - 1];
        }
        if (sn > 0 && sc > 0) {
            total += prefixSum[sn - 1][sc - 1];
        }

        return total;
    }

    public static boolean isNonOverlapping(int r1, int c1, int r2, int c2, int r3, int c3, int r4, int c4) {
        return r2 < r3 || r4 < r1 || c2 < c3 || c4 < c1;
    }
}