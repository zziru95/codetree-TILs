import java.io.*;
import java.util.*;

public class Main {
    static int maxV = Integer.MIN_VALUE;
    static char[] orders;
    static int n;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = br.readLine();
        orders = new char[temp.length()/2];
        int idx = 0;
        for(int i=0; i<temp.length(); i++){
            char now = temp.charAt(i);
            if(now =='+' || now == '-' || now =='*'){
                orders[idx] = now;
                idx++;
            }
        }
        n = orders.length;
        for(int i=1; i<5; i++){
            findMax(0,i);
        }

        System.out.print(maxV);
    }

    public static void findMax(int idx, int result){
        if(idx == n){
            maxV = Math.max(maxV,result);
            return;
        }

        for(int i=1;i<5; i++){
            if(orders[idx] == '+'){
                findMax(idx+1, i+result);
            } else if(orders[idx]== '-'){
                findMax(idx+1, result-i);
            } else if(orders[idx]=='*'){
                findMax(idx+1, result*i);
            }
        }
    }
}