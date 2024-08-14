import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static int[][] arr;
    static int[][] direction = { {0,1}, {0,-1}, {1,0}, {-1,0} };
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        bomb(r,c);
        replace();
        printAnswer();

    }

    public static void bomb(int r, int c) {
        int t = arr[r][c];

        for(int d=0; d<4; d++) {
            for(int i=0; i<t; i++) {
                int nr = r + direction[d][0] * i;
                int nc = c + direction[d][1] * i;
                if(0<=nr && nr<n && 0<=nc && nc<n) {
                    arr[nr][nc] = 0;
                }
            }
        }
    }

    public static void replace() {
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            int idx = n-1;
            for(int j=n-1; j>=0; j--) {
                if(arr[j][i] != 0) {
                    temp[idx][i] = arr[j][i];
                    idx--;
                }
            }
        }

        arr = temp;
    }

    public static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(arr[i][j]);
                if(j<n-1) sb.append(" ");
            }
            if(i<n-1) sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}