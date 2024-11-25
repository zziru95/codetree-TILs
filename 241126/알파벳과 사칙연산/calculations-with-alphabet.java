import java.io.*;
import java.util.*;

public class Main {
    static int maxV = Integer.MIN_VALUE;
    static char[] orders;
    static char[] alphas;
    static ArrayList<Integer> numnum = new ArrayList<>();
    static int n,m;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = br.readLine();
        orders = new char[temp.length()/2];
        alphas = new char[orders.length+1];
        int idx = 0;
        int idx2 = 0;
        Set<Character> set = new LinkedHashSet<>();
        for(int i=0; i<temp.length(); i++){
            char now = temp.charAt(i);
            if(now =='+' || now == '-' || now =='*'){
                orders[idx] = now;
                idx++;
            } else{
                set.add(now);
                alphas[idx2] = now;
                idx2++;
            }
        }
        n = orders.length;
        nums = new int[6];
        for(char c: set){
            numnum.add((int) c -'a');
        }
        m = numnum.size();
        comb(0);
        if(temp.length() ==1){
            maxV=4;
        }
        System.out.print(maxV);
    }


    public static void comb(int idx){
        if(idx == m){
            cal();
            return;
        }

        nums[numnum.get(idx)]= 1;
        comb(idx+1);
        nums[numnum.get(idx)]= 4;
        comb(idx+1);

    }


//b-a*b-c+b
    public static void cal(){
        int idx = 0;
        int start = nums[(int) alphas[0] -'a'];
        int idx2 = 1;
        while(idx<n){
            char alpha = alphas[idx2];
            if(orders[idx] == '+'){
                start += nums[(int) alpha -'a'];
            } else if(orders[idx]== '-'){
                start -= nums[(int) alpha -'a'];
            } else if(orders[idx]=='*'){
                start *= nums[(int) alpha -'a'];
            }
            idx++;
            idx2++;
        }

        maxV = Math.max(maxV,start);

    }
}