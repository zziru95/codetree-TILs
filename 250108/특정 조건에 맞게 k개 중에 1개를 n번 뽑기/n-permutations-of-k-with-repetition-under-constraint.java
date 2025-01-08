import java.util.*;
import java.io.*;

public class Main {
    static int K,N;
    static int[] comb;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        comb = new int[N];

        choose(0);
    }


    public static void choose(int idx){
        if(idx==N){
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            int prev = -1;
            for(int a : comb){
                if(prev ==a){
                    cnt++;
                    if(cnt>=2) return;
                } else{
                    cnt =0;
                    prev = a;
                }
                sb.append(a).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);

            return;
        }

        for(int i=1; i<=K; i++){
            comb[idx] = i;
            choose(idx+1);
        }
    }
}