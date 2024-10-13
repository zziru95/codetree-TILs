import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[] nums;
    static int sum;
    static int minD = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        nums = new int[2*n];
        sum =0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            sum += nums[i];
        }

        choose(0,0,0);
        System.out.print(minD);

    }

    public static void choose(int start, int cnt, int sum1){
        if(cnt==n){
            int sum2 = sum-sum1;
            minD = Math.min(minD,Math.abs(sum2-sum1));
            return;
        }

        for(int i=start; i<2*n; i++){
            choose(i+1,cnt+1,sum1+nums[i]);
        }
    }
}