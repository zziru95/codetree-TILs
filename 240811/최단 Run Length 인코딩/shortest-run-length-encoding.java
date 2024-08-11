import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        char[] charArray = str.toCharArray();
        int n = charArray.length;
        System.out.print(solve(charArray,n));

    }

    public static int solve(char[] charArray, int n) {
        StringBuilder answer = new StringBuilder();
        if (n==1) return 2;
        if (n==2) {
            if(charArray[0] == charArray[1]) {
                return 2;
            } else return 4;
        }
        char temp = charArray[0];
        char temp2 = charArray[1];

        for (int i=0; i<n; i++) {
            int now = 1-i;
            if (now<0) now += n;
            int next = now-2;
            if (next<0) next += n;
            charArray[now] = charArray[next];
        }

        charArray[2] = temp;
        charArray[3] = temp2;

        int cnt = 0;
        for (int i=0; i<n-1; i++) {
            cnt++;
            if (charArray[i] == charArray[i+1]) continue;

            answer.append(charArray[i]);
            answer.append(cnt);
            cnt = 0;
        }
        if (cnt>0) {
            cnt++;
            answer.append(charArray[n-1]);
            answer.append(cnt);
        } else {
            answer.append(charArray[n-1]);
            answer.append(1);
        }

        return answer.length();

    }
}