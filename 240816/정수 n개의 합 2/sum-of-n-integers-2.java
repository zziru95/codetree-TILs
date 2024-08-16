import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for(int i=1; i<n; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }

        int maxSum = prefix[k-1];

        for(int i=k; i<n; i++) {
            int temp = prefix[i]-prefix[i-k];
            maxSum = Math.max(maxSum, temp); 
        }

        System.out.print(maxSum);

    }
}