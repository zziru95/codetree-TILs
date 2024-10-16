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
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());;
            int end = Integer.parseInt(st.nextToken());
            sb.append(binSearch(start,end));

            if(i<m-1) sb.append('\n');
        }

        System.out.print(sb);
    }

    public static int binSearch(int start, int end){
        if(end<nums[0] || start > nums[n-1]) return 0;
        int left = 0;
        int right = n-1;
        int uIdx =n;
        int lIdx = n;
        while(left<=right){
            int mid = (left+right)/2;

            if(nums[mid]>end){
                right = mid-1;
                uIdx =Math.min(uIdx,mid);
            } else{
                left = mid +1;
            }
        }
        left = 0;
        right = n-1;
        while(left<= right){
            int mid = (left+right)/2;
            if(nums[mid] >= start){
                right = mid-1;
                lIdx = Math.min(lIdx,mid);
            } else{
                left = mid+1;
            }
        }

        return uIdx- lIdx;
    }
}