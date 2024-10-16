import java.util.*;
import java.io.*;


public class Main {
    static int n,m;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int[] result = binSearch(Integer.parseInt(st.nextToken()));
            if(result[0] == result[1]){
                sb.append(0);
            } else{
                sb.append(result[1]-result[0]);
            }
            if(i<m-1){
                sb.append('\n');
            }
        }

        System.out.print(sb);
    }



    public static int[] binSearch(int num){
        int left = 0;
        int right = n-1;
        int[] result = new int[] {n,n};
        while (left<=right){
            int mid = (left+right)/2;

            if(nums[mid]>=num){
                right = mid-1;

                result[0] = Math.min(result[0],mid);
            }
            else{
                left = mid + 1;
            }

        }
        left = 0;
        right = n-1;

        while (left<=right){
            int mid = (left+right)/2;

            if(nums[mid]>num){
                result[1] = Math.min(result[1],mid);
                right = mid-1;
            }
            else{
                left = mid + 1;
            }

        }

    
        return result;
    }
}