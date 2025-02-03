import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int INF = Integer.MAX_VALUE;
    static int[] jump, memo;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        
        jump = new int[n];
        memo = new int[n];
        Arrays.fill(memo,INF);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            jump[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);
        System.out.print((memo[n-1] != INF) ? memo[n-1] : -1);

    }


    public static void dfs(int idx, int t){
        if(idx>=n){
            return;
        }

        if(memo[idx] < t) return;
        memo[idx] = t;
        int possible = jump[idx];

        for(int i=1; i+idx<n && i<=possible; i++){
            dfs(idx+i,t+1);
        }
    }
}