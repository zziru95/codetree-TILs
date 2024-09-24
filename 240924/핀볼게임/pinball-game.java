import java.util.*;
import java.io.*;


public class Main {
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} }; // 상하우좌
    static int[] bar2 = {3,2,1,0};
    static int[][] frame;
    static int n;
    static int maxValue;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n= Integer.parseInt(st.nextToken());
        frame = new int [n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                frame[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxValue = Integer.MIN_VALUE;

        for(int i=0; i<n; i++){
            play(i,0,3);
            play(i,n-1,2);
            play(0,i,1);
            play(n-1,i,0);
        }

        System.out.print(maxValue);
    }

    public static void play(int r, int c, int d){
        int time = 1;
        while(true) {
            r += direction[d][0];
            c += direction[d][1];
            time++;
            
            if(!(0<=r && r<n && 0<=c && c<n)) {
                break;
            }

            if(frame[r][c] == 1) {
                d = (d+2)%4;
            } else if(frame[r][c] == 2) {
                d = bar2[d];
            }
        }

        maxValue = Math.max(maxValue,time);
    }
}