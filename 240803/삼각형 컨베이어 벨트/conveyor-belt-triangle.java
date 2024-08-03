import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] triangle = new int[3][n];

        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t=0; t<T; t++) {
            int temp1 = triangle[2][n-1];
            int temp2 = triangle[0][n-1];
            int temp3 = triangle[1][n-1];

            for(int i=1; i<n; i++) {
                triangle[0][n-i] = triangle[0][n-i-1];
                triangle[1][n-i] = triangle[1][n-i-1];
                triangle[2][n-i] = triangle[2][n-i-1];
            }
            triangle[0][0] = temp1;
            triangle[1][0] = temp2;
            triangle[2][0] = temp3;
        }

        for(int i=0; i<3; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++) {
                sb.append(triangle[i][j]);

                if(j<n-1) sb.append(" ");
            }
            System.out.println(sb.toString());
        }

    }
}