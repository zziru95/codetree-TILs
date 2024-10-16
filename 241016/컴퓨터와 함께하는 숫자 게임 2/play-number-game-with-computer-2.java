import java.util.*;
import java.io.*;

public class Main {
    static int m;
    static int a,b;
    static int maxT = 0;
    static int minT = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        for(int i=a; i<=b; i++){
            baseball(i);
        }
        System.out.print(minT+" "+maxT);
    }

    public static void baseball(int num){
        int left = 1;
        int right = m;
        int cnt =1;
        while(left<=right){
            int mid = (left+right) /2;
            if(mid == num) break;

            cnt++;
            if(mid>num){
                right = mid-1;
            } else{
                left = mid+1;
            }
        }
        maxT = Math.max(maxT,cnt);
        minT = Math.min(minT,cnt);
    }
}