import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder(br.readLine());

        char first = answer.charAt(0);
        answer.deleteCharAt(0);
        answer.append(first);
        System.out.print(answer);

    }
}