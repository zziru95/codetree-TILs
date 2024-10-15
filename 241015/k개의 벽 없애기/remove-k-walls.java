import java.util.*;
import java.io.*;

public class Main {
    static int n,k;
    static int[][] direction = { {-1,0},{1,0},{0,1},{0,-1} };
    static boolean[][] visited;
    static int[][] arr;
    static int[][] walls;

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        walls = new int[k][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        
    }
}