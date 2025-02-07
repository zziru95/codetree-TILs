import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[] arr, dp;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dp = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n;i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        } 
        int answer = arr[0];
        dp[0] = arr[0];
        for(int i=1; i<n; i++){
            dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
            answer = Math.max(dp[i], answer);
        }
        System.out.print(answer);
    }
}