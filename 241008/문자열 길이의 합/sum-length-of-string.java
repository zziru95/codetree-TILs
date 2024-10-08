import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String[] strList = new String[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            strList[i]= st.nextToken();
        }
        int cnt = 0;
        int aCnt = 0;
        for(String temp : strList){
            cnt = cnt + temp.length();
            if(temp.startsWith("a")) {
                aCnt++;
            }
        }

        StringBuilder sb =new StringBuilder();
        sb.append(cnt+" "+aCnt);
        System.out.print(sb);
    }
}