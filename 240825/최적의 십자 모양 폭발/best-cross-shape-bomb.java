import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[][] graph;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int maxValue = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bomb(i, j);
            }
        }

        System.out.println(maxValue);

    }


    public static void bomb(int r, int c) {
        int[][] temp = new int[n][];
        for (int i = 0; i < n; i++) {
            temp[i] = graph[i].clone();
        }

        int t = temp[r][c];
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < t; i++) {
                int nr = r + direction[d][0] * i;
                int nc = c + direction[d][1] * i;

                if (0 <= nr && nr < n && 0 <= nc && nc < n) {
                    temp[nr][nc] = 0;
                }
            }
        }
        maxValue = Math.max(maxValue, count(temp));

    }

    public static int[][] gravity(int[][] graph) {
        int[][] temp = new int[n][n];
        for (int j = 0; j < n; j++) {
            int idx = n-1;
            for (int i = 0; i < n; i++) {
                if (graph[i][j] != 0) {
                    temp[idx][j] = graph[i][j];
                    idx--;
                }
            }
        }
        return temp;
    }

    public static int count(int[][] graph) {
        int cnt = 0;
        int temp = 1;
        for (int i = 0; i < n; i++) {
            temp = 1;
            for (int j = 1; j < n; j++) {
                if (graph[i][j] != 0 && graph[i][j] == graph[i][j - 1]) {
                    temp++;
                } else {
                    if (temp ==2) {
                        cnt++;
                        temp =1;
                    }

                }
            }
            if (temp ==2) {
                cnt++;
            }

        }

        for (int j = 0; j < n; j++) {
            temp = 1;
            for (int i = 1; i < n; i++) {
                if (graph[i][j] != 0 && graph[i][j] == graph[i - 1][j]) {
                    temp++;
                } else {
                    if (temp ==2) {
                        cnt++;
                        temp = 1;
                    }
                }
            }
            if (temp ==2) {
                cnt++;
            }

        }


        return cnt;
    }


    public static void printArr(int[][] graph) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(graph[i][j]);
                if (j < n - 1) {
                    sb.append(" ");
                }
            }
            if (i < n - 1) {
                sb.append(System.lineSeparator());
            }
        }
        System.out.println(sb);
    }
}