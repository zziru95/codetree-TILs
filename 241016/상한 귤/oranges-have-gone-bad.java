import java.util.*;
import java.io.*;

public class Main {
    static int n,k;
    static int[][] direction = { {-1,0},{1,0},{0,-1},{0,1} };
    static int[][] arr;
    static ArrayList<int[]> ill;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        ill = new ArrayList<>();
        
        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==2){
                    ill.add(new int[] {i,j});
                    q.add(new int[] {i,j,2});
                }
            }
        }


        while(!q.isEmpty()){
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int t = current[2];
            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(0<=nr && nr<n && 0<=nc && nc<n && arr[nr][nc]==1){
                    arr[nr][nc]= t+1;
                    q.add(new int[] {nr,nc,t+1});
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j] ==0){
                    sb.append(-1);
                } else if(arr[i][j] ==1){
                    sb.append(-2);
                } else{
                    sb.append(arr[i][j]-2);
                }

                if(j<n-1){
                    sb.append(" ");
                }
            }
            if(i<n-1){
                sb.append(System.lineSeparator());
            }
        }

        System.out.print(sb);



    }
}