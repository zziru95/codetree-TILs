import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] kind = new int[N];
        int[] dp = new int[M+1];
        for(int i=0; i<N; i++){
            kind[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int a : kind){
            if(a> M) continue;
            dp[a] = 1;
        }

        for(int i=1; i<=M; i++){
            int min = 50000;
            for(int a : kind){
                if (i-a <0) continue;
                if(dp[i-a] == 0) continue;
                min = Math.min(min, dp[i-a]+1);
                if(dp[i] != 0){
                    min = Math.min(dp[i], min);
                }
                dp[i] = min;
            }
        }

        System.out.print(dp[M] != 0 ? dp[M] : -1);
    }
}