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


        for(int i=0; i<=n-m; i++) {
            boolean check1 = false;
            boolean check2 = false;
            int j=0;
            int temp = 1;
            int temp2 = 1;
            while(j<n-1) {
                if(graph[i][j] == graph[i][j+1]) {
                    temp++;
                    } else {
                        temp = 1;
                        }
                if(temp >= m) check1 = true;

                if(graph[j][i] == graph[j+1][i+1]) {temp2++;}
                else {temp2=1;}
                if(temp2 >= m ) check2 = true;

                j++;
        
            }

            if(check1) answer++; // 세로 방향이 일치하면 결과 증가
            if(check2) answer++; // 가로 방향이 일치하면 결과 증가
        }
        System.out.println(answer);
    }
}