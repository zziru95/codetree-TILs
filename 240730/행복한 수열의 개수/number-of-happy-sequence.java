import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];
        int answer = 0;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer += checkRow(graph,n,m);
        answer += checkCol(graph,n,m);

        System.out.println(answer);
        
    }

    public static int checkRow(int[][] graph, int n, int m) {
        if (n==1) return 1;
        if (m==1) return n;
        int cnt = 0;
        for(int i=0; i<n;i++) {
            boolean check = false;
            int temp = 1;
            for(int j=0; j<n-1; j++) {
                if(check) break;

                if(graph[j][i] == graph[j+1][i]) {
                    temp++;
                    if(temp == m) check = true;
                } else {
                    temp = 1;
                }

            }
            if (check) cnt++;
        }

        return cnt;
    }

    public static int checkCol(int[][] graph, int n, int m) {
        if (n==1) return 1;
        if (m==1) return n;
        int cnt = 0;
        for(int i=0; i<n;i++) {
            boolean check = false;
            int temp = 1;
            for(int j=0; j<n-1; j++) {
                if(check) break;

                if(graph[i][j] == graph[i][j+1]) {
                    temp++;
                    if(temp == m) check = true;
                } else {
                    temp = 1;
                }

            }
            if (check) cnt++;
        }

        return cnt;
    }
}