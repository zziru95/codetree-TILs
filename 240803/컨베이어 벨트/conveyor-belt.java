import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[][] belt = new int[2][n];
        for(int i=0; i<2; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                belt[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<t;i++) {
            int temp1 = belt[0][n-1];
            int temp2 = belt[1][n-1];
            for(int j=0; j<n-1; j++){
                belt[0][n-j-1] = belt[0][n-j-2] ;
                belt[1][n-j-1] = belt[1][n-j-2];
            }
            belt[0][0] = temp2;
            belt[1][0] = temp1;


        }

        for(int i=0; i<2;i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++){
                sb.append(belt[i][j]);
                sb.append(" ");
            }
            System.out.println(sb.toString().trim());
        }

    }
}