import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static int[][] graph;
    static int[][] direction = { {-1,1}, {-1,-1}, {1,-1}, {1,1} };
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int m3 = Integer.parseInt(st.nextToken());
        int m4 = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        if(dir==0) turnClockReverse(r,c,m1,m2,m3,m4);
        else turnClock(r,c,m1,m2,m3,m4);

        StringBuilder sb =new StringBuilder();

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(graph[i][j]);
                if(j<n-1) sb.append(" ");
            }

            if(i<n-1) sb.append(System.lineSeparator());
        }

        System.out.print(sb.toString());



    }

    public static void turnClock(int r, int c, int m1, int m2, int m3, int m4) {
        int temp = graph[r][c];
        int cr = r;
        int cc = c;
        for(int i=0; i<m1; i++) {

            int nr = cr + direction[0][0];
            int nc = cc + direction[0][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }

        for(int i=0; i<m2; i++) {

            int nr = cr + direction[1][0];
            int nc = cc + direction[1][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }

        for(int i=0; i<m1; i++) {

            int nr = cr + direction[2][0];
            int nc = cc + direction[2][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }

        for(int i=0; i<m2; i++) {

            if(i== m2-1) {
                graph[cr][cc] = temp;
                continue;
            }

            int nr = cr + direction[3][0];
            int nc = cc + direction[3][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }
    }



    public static void turnClockReverse(int r, int c, int m1, int m2, int m3, int m4) {
        int temp = graph[r][c];
        int cr = r;
        int cc = c;
        //2,1,2,1
        for(int i=0; i<m2; i++) {

            int nr = cr + direction[1][0];
            int nc = cc + direction[1][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }

        for(int i=0; i<m1; i++) {

            int nr = cr + direction[0][0];
            int nc = cc + direction[0][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }

        for(int i=0; i<m2; i++) {

            int nr = cr + direction[3][0];
            int nc = cc + direction[3][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }

        for(int i=0; i<m1; i++) {

            if(i== m1-1) {
                graph[cr][cc] = temp;
                continue;
            }

            int nr = cr + direction[2][0];
            int nc = cc + direction[2][1];

            graph[cr][cc] = graph[nr][nc];
            cr = nr;
            cc = nc;
        }
    }



}