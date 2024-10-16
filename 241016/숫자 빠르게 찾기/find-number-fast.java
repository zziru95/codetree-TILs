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
        nums = new int [n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb =new StringBuilder();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            sb.append(binSearch(Integer.parseInt(st.nextToken())));
            if(i<m-1){
                sb.append('\n');
            }
        }
        System.out.print(sb);

    }


    public static int binSearch(int num){
        int left = 0;
        int right = n-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]== num){
                return mid+1;
            }
            if(nums[mid]<num){
                left = mid+1;
            } else if(nums[mid]>num){
                right = mid-1;
            }
        }
        return -1;
    }
}