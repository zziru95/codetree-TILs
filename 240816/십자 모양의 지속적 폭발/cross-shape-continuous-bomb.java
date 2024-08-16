import java.util.*;
import java.io.*;


public class Main {
    static int[][] graph;
    static int n,m;
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} };



    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            bomb(Integer.parseInt(st.nextToken())-1);
            gravity();
        }
        printAnswer();


    }

    public static void bomb(int c) {

        for(int i=0; i<n; i++) {
            if(graph[i][c] != 0) {
                int cnt = graph[i][c];

                for(int d=0; d<4; d++) {
                    for(int k=0; k<cnt; k++) {
                        int nr = i + direction[d][0] * k;
                        int nc = c + direction[d][1] * k;
                        if(0<=nr && nr<n && 0<=nc && nc<n) {
                            graph[nr][nc] = 0;
                        }
                    }
                }
                return;
            }
        }


    }

    public static void gravity() {
        int[][] temp = new int[n][n];
        for(int j=0; j<n; j++) {
            int idx = n-1;
            for(int i=n-1; i>=0; i--) {
                if(graph[i][j] !=0) {
                    temp[idx][j] = graph[i][j];
                    idx--;
                }
            }
        }

        graph = temp;
    }

    public static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(graph[i][j]);
                if(j<n-1) sb.append(" ");
            }
            if(i < n - 1) sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}