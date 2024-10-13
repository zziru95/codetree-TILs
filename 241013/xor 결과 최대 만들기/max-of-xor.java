import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int maxV = 0;
    static int[] nums;
    static int[] comb;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];
        comb = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken()); 
        }
        combination(0,0);
        System.out.print(maxV);

    }


    public static void combination(int start, int cnt){
        if(cnt==m){
            int temp = comb[0];
            for(int i=1; i<m; i++){
                temp = temp^ comb[i];
            }
            maxV = Math.max(maxV, temp);
            return;
        }

        for(int i=start;i<n;i++){
            comb[cnt] = nums[i];
            combination(i+1,cnt+1);
        }
    }
}