import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] dp;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        //dp[현재층수][1번오른 횟수]
        dp = new int[N+1][3];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //1층오른 초기값만 지정 1층올랐으니 [1층][1층오르기 1번]->[1][1]
        dp[1][1] = arr[1];
        
        //2계단 올라간것부터 쌓아가기 시작(1계단 올라간건 초기값으로 넣었으니까)
        for(int i=2; i<=N; i++){
            //1계단씩 몇번 올랐는지 0번,1번,2번
            for(int j=0; j<4; j++){
                dp[i][j] = Math.max(dp[i-2][j] + arr[i], dp[i][j]);
                
                if(j!=3){
                    dp[i][j+1] = Math.max(dp[i-1][j] + arr[i],dp[i][j+1]);
                }
            }
        }
        int answer = Math.max(dp[N][0],dp[N][1]);
        answer = Math.max(answer,dp[N][2]);
        System.out.print(answer);
                                       
    }
}