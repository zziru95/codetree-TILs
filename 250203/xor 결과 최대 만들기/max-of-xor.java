import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[] nums,comb;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];
        comb = new int[m];
        st = new StringTokenizer(br.readLine());
        
        for(int i=0 ; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        choose(0,0);
        System.out.print(answer);

    }

    public static void choose(int start, int idx){
        if(idx==m){
            int result = 0;
            for(int a : comb){
                result ^= a;
            }
            answer = Math.max(result,answer);
            return;
        }

        for(int i=start; i<n;i++){
            comb[idx] = nums[i];
            choose(i+1, idx+1);
        }
    }
}