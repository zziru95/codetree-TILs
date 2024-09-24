import java.util.*;
import java.io.*;
public class Main {
    static int n,m,t;
    static int[][] arr;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1}};



    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        int count = m;
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int[] temp = {r,c};
            q.add(temp);
        }

        for(int time=0; time<=t; time++){
            int[][] temp = new int [n][n];
            int tempCnt = 0;
            while(!q.isEmpty()) {
                int[] temp2 = q.poll();
                int r = temp2[0];
                int c = temp2[1];
                int maxValue = -2626262;
                int tempR = -1;
                int tempC = -1;
                for(int d=0; d<4; d++){
                    int nr = r + direction[d][0];
                    int nc = c + direction[d][1];

                    if( 0<= nr && nr< n && 0<= nc && nc< n){
                        if(arr[nr][nc]> maxValue) {
                            maxValue = arr[nr][nc];
                            tempR = nr;
                            tempC = nc;
                        }
                    }
                }

                
                temp[tempR][tempC] += 1;
            }
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(temp[i][j] == 1) {
                        tempCnt++;
                        q.add(new int[] {i,j});
                    }
                }
            }
            count = tempCnt;
        }
        

        System.out.print(count);

    }
}