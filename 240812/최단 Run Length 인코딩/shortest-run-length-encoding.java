import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        int n = str.length();
        System.out.print(solve(str,n));
    }

    public static int solve(String str, int n) {
        
        if (n==1) return 2;
        if (n==2) {
            if(str.charAt(0) == str.charAt(1)) {
                return 2;
            } else return 4;
        }
        int minValue = 9999999;
        for(int q=0; q<n; q++) {
            StringBuilder answer = new StringBuilder();
            String newStr = str.substring(n-q,n) + str.substring(0,n-q);
            char[] charArray = newStr.toCharArray();
            
            int cnt = 1;
            for (int i=1; i<n; i++) {
                if (charArray[i] == charArray[i-1]) cnt++;
                else {
                    answer.append(charArray[i-1]).append(cnt);
                    cnt=1;
                }
            }
            answer.append(charArray[n-1]).append(cnt);
            minValue = Math.min(answer.length(),minValue);


        }
        
        return minValue;
    }
}