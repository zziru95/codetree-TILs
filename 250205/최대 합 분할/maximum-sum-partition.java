import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[][] dp;
    static int totalSum;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        arr = new int[n];
        totalSum = 0;

        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<n;i++){
            arr[i]= Integer.parseInt(st.nextToken());
            totalSum += arr[i];
        }

        int target = totalSum/2;
        dp = new boolean[target+1][target+1];
        dp[0][0] = true;
        

        for (int m = 0; m < n; m++) {
            int num = arr[m];

            // **역방향 순회** (큰 값부터 작은 값 순서로)
            for (int i = target; i >= 0; i--) {
                for (int j = target; j >= 0; j--) {
                    if (dp[i][j]) {  // 현재 상태가 가능하면 갱신
                        if (i + num <= target) dp[i + num][j] = true;  // A 그룹에 추가
                        if (j + num <= target) dp[i][j + num] = true;  // B 그룹에 추가
                    }
                }
            }
        }

        for(int i = target; i>=0; i--){
            if(dp[i][i]){
                 System.out.println(i);
                 break;
            }
        }

        // for (int i = 0; i <= target; i++) {
        //     for (int j = 0; j <= target; j++) {
        //         System.out.print(dp[i][j] ? "1 " : "0 ");
        //     }
        //     System.out.println();
        // }

    }
}   