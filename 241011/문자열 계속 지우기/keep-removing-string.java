import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int n = B.length();
        int check = -2;
        while((check= A.indexOf(B)) != -1){
            A = A.substring(0, check) + A.substring(check+n);
        }
        System.out.print(A);
    }
}