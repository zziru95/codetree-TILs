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
        int temp;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=0; i<=n-m; i++) {
            for(int j=0; j<=-n-m; j++) {
                temp = graph[i][j];
                boolean check1 = true;
                boolean check2 = true;
                for(int k=1; k<=m; k++) {
                    if (graph[i+k][j] != temp) check1 = false;
                    if (graph[i][j+k] != temp) check2 = false;
                }
                if(check1) answer++;
                if(check2) answer++;
            }
        }
        System.out.println(answer);
    }
}