import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[][] arr;
    static boolean[] visited;
    static int answer = Integer.MIN_VALUE;
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

        comb(0,Integer.MAX_VALUE);
        System.out.print(answer);



    }


    public static void comb(int idx, int min){
        if (idx == n){
            answer = Math.max(answer,min);
            return;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                int temp = arr[i][idx];
                comb(idx+1, Math.min(min,temp));
                visited[i] = false;
            }
        }
    }

}