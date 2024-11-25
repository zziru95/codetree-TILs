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
            
        findMax(0,4);

        System.out.print(maxV);
    }

    public static void findMax(int idx, int result){
        if(idx == n){
            maxV = Math.max(maxV,result);
            return;
        }

    
        if(orders[idx] == '+'){
            findMax(idx+1, 4+result);
        } else if(orders[idx]== '-'){
            findMax(idx+1, result-1);
        } else if(orders[idx]=='*'){
            if(result>0){
                findMax(idx+1, result*4);
            } else{
                findMax(idx+1, result*1);
            }
            
        }
        
    }
}