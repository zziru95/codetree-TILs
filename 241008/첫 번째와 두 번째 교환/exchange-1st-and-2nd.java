import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String temp = st.nextToken();
        char[] temp2 = temp.toCharArray();
        char first = temp2[0];
        char second = temp2[1];
        for(int i=0; i<temp2.length; i++){
            if(temp2[i] == first) {
                temp2[i] = second;
            } else if(temp2[i] == second) {
                temp2[i] = first;
            }
        }
        System.out.print(String.valueOf(temp2));
    }
}