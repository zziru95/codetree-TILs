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

        int maxCnt = 0;
        for (int r1 = 0; r1 < n ; r1++) {
            for (int c1 = 0; c1 < m; c1++) {
                for (int r2 = r1; r2 < n; r2++) {
                    for (int c2 = c1; c2 < m; c2++) {
                        if (check(r1, c1, r2, c2) && check2(matrix, r1, c1, r2, c2)) {
                            if (maxCnt < (r2 - r1 + 1) * (c2 - c1 + 1)) {
                                maxCnt = (r2 - r1 + 1) * (c2 - c1 + 1);
                            }

                        }
                    }
                }
            }
        }
        if (maxCnt == 0) maxCnt= -1; 

        System.out.println(maxCnt);
    }
    public static boolean check(int r1, int c1, int r2, int c2) {
        return (r2>=r1 && c2>=c1);
    }

    public static boolean check2(int[][] matrix, int r1, int c1, int r2, int c2) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (matrix[i][j] <= 0) {
                    return false;
                }
            }
        }
        return true;

    }
}