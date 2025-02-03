import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[][] arr;
    static int answer = 0;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new boolean[n];
    
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        choose(0,0);
        System.out.print(answer);
    }


    public static void choose(int idx, int sum){
        if(idx == n){
            answer = Math.max(answer,sum);
            return;
        }

        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            visited[i] = true;
            choose(idx+1, sum+arr[idx][i]);
            visited[i] = false;
        }
    }
}