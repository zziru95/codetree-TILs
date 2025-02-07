import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] A, B;
    static int[][] dp;

    public static int solve(int a , int b){
        if(a==N || b==N){
            return 0;
        }

        if(dp[a][b] != -1){
            return dp[a][b];
        }

        if(A[a]>B[b]){
            dp[a][b] = Math.max(dp[a][b], solve(a,b+1)+B[b]);
        }

        
        if(A[a]<B[b]){
            dp[a][b] = Math.max(dp[a][b], solve(a+1,b));
        }
        dp[a][b] = Math.max(dp[a][b], solve(a+1,b+1));

        return dp[a][b];
    }


    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        A = new int[N];
        B = new int[N];
        dp = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.print(solve(0,0));


    }
}