import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] dp;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        if(sum%2 == 1){
            System.out.print("No");
            return;
        }

        int target = sum/2;
        dp = new boolean[target+1];
        dp[0] = true;

        for(int num : arr){
            for(int i = target; i>=num; i--){
                dp[i] = dp[i] || dp[i-num];
            }
        }
        
        System.out.print(dp[target] ? "Yes" : "No");


        

        
    }
}