import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String answer = br.readLine();
        String temp;
        while((temp = br.readLine()) != null && !temp.isEmpty()){
            int n = answer.length();
            int m = Integer.parseInt(temp);
            if(m>=n){
                answer = answer.substring(0,n-1);
            } else{
                answer = answer.substring(0,m) + answer.substring(m+1);
            }
            System.out.println(answer);
        }

                
    }
}