import java.util.*;
import java.io.*;

public class Main {
    static int n, sum;
    static int[] arr;
    static boolean[] dp; 

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        sum = 0;
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        int target = sum/2;
        dp = new boolean[target+1];
        dp[0] = true;
        for(int a: arr){
            for(int i=target; i>=a; i--){
                dp[i] = dp[i] || dp[i-a];
            }
        }

        for(int i=target; i>=0; i--){
            if(dp[i]){
                System.out.print(Math.abs(2*i-sum));
                break;
            }
        }
    }
}