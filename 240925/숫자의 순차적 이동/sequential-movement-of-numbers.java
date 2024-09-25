import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] arr;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1}, {-1,-1}, {-1,1}, {1,1}, {1,-1} };
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int [n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n*n; j++){
                move(j);
            }
        }
        printAnswer();

    }

    public static void move(int num){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j] == num) {
                    int maxValue = -1;
                    int newR = i;
                    int newC = j;
                    for(int d= 0; d<8; d++) {
                        int nr = i + direction[d][0];
                        int nc = j + direction[d][1];

                        if(0<=nr && nr<n && 0<=nc && nc<n) {
                            if(arr[nr][nc]> maxValue){
                                maxValue = arr[nr][nc];
                                newR = nr;
                                newC = nc;
                            }
                        }
                    }
                    int temp = arr[i][j];
                    arr[i][j] = maxValue;
                    arr[newR][newC] = temp;
                    return;
                }
            }
        }
    }

    public static void printAnswer(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                sb.append(arr[i][j]);
                if(j<n-1) sb.append(" ");
            }
            if(i<n-1) sb.append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}