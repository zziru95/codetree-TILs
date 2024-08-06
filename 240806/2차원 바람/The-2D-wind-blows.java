import java.util.*;
import java.io.*;   
public class Main {
    static int N, M, Q;
    static int[][] matrix;
    static int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        for(int q=0; q<Q; q++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;

            turn(r1,c1,r2,c2);
            avg(r1,c1,r2,c2);
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(matrix[i][j]);

                if(j<M-1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }


    public static void turn(int r1, int c1, int r2, int c2) {
        int temp = matrix[r1][c1];
        int nr = r1;
        int nc = c1;
        int d = 0;
        int t = 0;
        
        while(t<(r2-r1)) {
            matrix[nr][nc] = matrix[nr+1][nc];
            nr++;
            t++;
        }

        t=0;
        while(t<(c2-c1)){
            matrix[nr][nc] = matrix[nr][nc+1];
            nc++;
            t++;
        }
        t=0;
        while(t<(r2-r1)) {
            matrix[nr][nc] = matrix[nr-1][nc];
            nr--;
            t++;
        }
        t=0;
        while(t<(c2-c1-1)) {
            matrix[nr][nc] = matrix[nr][nc-1];
            nc--;
            t++;
        }
        matrix[r1][c1+1] = temp;
    }


    public static void avg(int r1, int c1, int r2, int c2) {
        int n = r2-r1+1;
        int m = c2-c1+1;
        int[][] recordAvg = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int temp = matrix[r1+i][c1+j];
                int cnt = 1;
                for(int d=0; d<4; d++) {
                    int nr = r1+i+direction[d][0];
                    int nc = c1+j+direction[d][1];
                    if(0<=nr && nr< N && 0<=nc && nc<M) {
                        temp += matrix[nr][nc];
                        cnt++;
                    }
                }
                recordAvg[i][j] = temp / cnt;
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                matrix[r1+i][c1+j] = recordAvg[i][j];
            }
        }
    }
}