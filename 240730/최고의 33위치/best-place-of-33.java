import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] frame = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                frame[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for(int i=0; i<=N-3; i++) {
            for(int j=0; j<=N-3; j++) {
                int temp =0;
                for(int k=0; k<3; k++) {
                    for(int m=0; m<3; m++) {
                        if (frame[i+k][j+m] == 1) temp++;

                    }
                }
                cnt = Math.max(cnt,temp);
            }
        }

        System.out.println(cnt);
    }
}