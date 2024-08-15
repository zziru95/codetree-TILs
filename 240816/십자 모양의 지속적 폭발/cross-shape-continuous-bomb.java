import java.util.*;
import java.io.*;


public class Main {
    static int[][] graph;
    static int n,m;
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} };



    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            bomb(Integer.parseInt(st.nextToken()));
        }


    }

    public static void
}