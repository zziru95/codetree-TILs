import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int r,c;
    static char[][] miro;
    static int[][] direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // 우,상,좌,하

    static int[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        miro = new char[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < n; j++) {
                miro[i][j] = line.charAt(j);
                visited[i][j] = -1;
            }
        }
        visited[r][c] = 0;
        System.out.println(goMiro(r,c));

    }

    public static int goMiro(int r, int c) {
        int d = 0;
        int cnt = 0;
        int tCnt = 0;
        while (true) {
            //다음곳
            int nr = r + direction[d][0];
            int nc = c + direction[d][1];
            //벽위치
            int wr = r + direction[(d+3)%4][0];
            int wc = c + direction[(d+3)%4][1];
            if (checkIndex(nr,nc) && visited[nr][nc] == d) return -1;
            if (tCnt>4) return -1;
            if (checkIndex(nr, nc) && miro[nr][nc] == '#') {
                d = (d + 1) % 4;
                tCnt++;
                continue;
            }

            if (checkIndex(wr,wc) && miro[wr][wc] == '#' && !checkIndex(nr,nc)) return cnt+1;

            if (checkIndex(nr, nc) && miro[nr][nc] == '.' && checkIndex(wr, wc) && miro[wr][wc] == '#') {
                cnt++;
                tCnt = 0;
                r = nr;
                c = nc;
                visited[r][c] = d;
                wr = r + direction[(d+3)%4][0];
                wc = c + direction[(d+3)%4][1];
                if (checkIndex(wr, wc) && miro[wr][wc] == '.') {
                    cnt++;
                    d = (d + 3) % 4;
                    r = r + direction[d][0];
                    c = c + direction[d][1];
                    visited[r][c] = d;
                }

                continue;
            }




            break;
        }

        return -1;
    }

    public static boolean checkIndex(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}