import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        String[] check = new String[5];
        check[0] = "apple";
        check[1] = "banana";
        check[2] = "grape";
        check[3] = "blueberry";
        check[4] = "orange";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char temp = st.nextToken().charAt(0);

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(String temp2 : check){
            char[] temp3 = temp2.toCharArray();
            if(temp == temp3[2] || temp == temp3[3]) {
                sb.append(temp2);
                sb.append(System.lineSeparator());
                cnt++;
            }
        }
        sb.append(cnt);
        System.out.print(sb);

    }
}