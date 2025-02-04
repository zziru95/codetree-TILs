import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[] dp,kind;

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        kind = new int[N];
        dp = new int[M+1];
        for(int i=0; i<N; i++){
            kind[i] = Integer.parseInt(st.nextToken());
        }
        // reverse(kind);
        for(int a: kind){
            if(a>M) continue;
            dp[a] = 1;
        }

        for(int i=1; i<=M;i++){
            for(int a: kind){
                int cnt = 0;
                if(i-a>=0){
                    cnt = dp[i-a]+1;
                }
                dp[i] = Math.max(dp[i], cnt);
            }
        }

        System.out.print(dp[M] != 0 ? dp[M] : -1);
        
    }

    public static void reverse(int[] lst){
        int left = 0;
        int right = lst.length-1;
        while(left<right){
            int temp = lst[left];
            lst[left] = lst[right];
            lst[right] = temp;
            left++;
            right--;
        }
    }
}