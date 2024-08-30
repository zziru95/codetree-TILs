import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static int[][] blocks;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken()) - 1;
        blocks = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                blocks[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        fallingBlock(m,k);
        printAnswer();

    }

    public static void printAnswer(){
        StringBuilder sb =new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(blocks[i][j]);
                if(j<n-1) {
                    sb.append(" ");
                }
            }
            if(i<n-1) {
                sb.append(System.lineSeparator());
            }
        }
        System.out.print(sb);
    }

    public static void fallingBlock(int m, int k){
        int start = k;
        int end = k+m;
        for(int r=0; r<n-1; r++) {
            for(int i = start; i<=end; i++) {
                if(blocks[r+1][i] == 1) {
                    for(int j = start; j<= end; j++) {
                        blocks[r][j] = 1;
                    }
                    return;
                }
            }
        }
        for(int j = start; j<= end; j++) {
            blocks[n-1][j] = 1;
        }
        return;
    }
}