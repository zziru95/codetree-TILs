import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder(br.readLine());
        answer.deleteCharAt(answer.indexOf("e"));
        System.out.print(answer);

    }
}