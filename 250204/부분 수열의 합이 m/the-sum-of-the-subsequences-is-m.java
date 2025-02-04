import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] kind = new int[n];
        int[] dp = new int[m+1];
        for(int i=0; i<n; i++){
            kind[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n; i++){
            int now = kind[i];
            
            for(int j=m; j>=0; j--){
                
                if(dp[j] != 0 && j+now <=m){
                    int min = dp[j] +1;
                    if(dp[j+now] != 0){
                        min = Math.min(min, dp[j+now]);
                    }
                    dp[j+now] = min;
                }
            }
            if(now <= m) dp[now] = 1;
        }
        
        
        System.out.print(dp[m] != 0 ? dp[m] : -1);
    }
}