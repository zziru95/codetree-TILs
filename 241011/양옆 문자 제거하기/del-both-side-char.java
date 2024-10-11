import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String temp = st.nextToken();
        int n = temp.length();
        String answer = temp.substring(0,1) + temp.substring(2,n-2) + temp.substring(n-1);
        System.out.print(answer);
    }
}