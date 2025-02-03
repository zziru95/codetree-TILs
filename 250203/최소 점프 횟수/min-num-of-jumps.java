import java.util.*;
import java.io.*;

public class Main {
    static int n;
    
    static int[] jump, memo;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        
        jump = new int[n];
        memo = new int[n];
        Arrays.fill(memo,-1);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            jump[i] = Integer.parseInt(st.nextToken());
        }
        bfs();
        System.out.print(memo[n-1]);

    }


    public static void bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0,0});

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int idx = curr[0];
            int t = curr[1];

            
            int possible = jump[idx];

            for(int i=1; i+idx<n && i<=possible; i++){
                if(memo[idx+i]==-1){
                    memo[idx+i] = t+1;
                    q.add(new int[] {idx+i,t+1});
                }        
            }
        
        }
    }
}