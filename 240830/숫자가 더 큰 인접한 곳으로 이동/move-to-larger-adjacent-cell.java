import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,r,c;
    static int[][] arr;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken())-1;
        c = Integer.parseInt(stringTokenizer.nextToken())-1;
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(arr[r][c]);


        while (true) {
            int[] next = check(r,c);

            if (next[0] == -1) {
                break;
            } else {
                r = next[0];
                c = next[1];
                sb.append(" ");
                sb.append(arr[r][c]);
            }

        }


        System.out.println(sb);

    }

    public static int[] check(int r, int c) {

        int tr = -1;
        int tc = -1;

        for (int d = 0; d < 4; d++) {

            int nr = r + direction[d][0];
            int nc = c + direction[d][1];

            if (0 <= nr && nr < n && 0 <= nc && nc < n) {
                if (arr[nr][nc] > arr[r][c]) {
                    tr = nr;
                    tc = nc;
                    return new int[]{tr, tc};
                }
            }

        }
        return new int[]{tr, tc};
    }
}