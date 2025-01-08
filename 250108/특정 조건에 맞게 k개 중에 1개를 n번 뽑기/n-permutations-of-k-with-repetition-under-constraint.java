import java.util.*;
import java.io.*;

public class Main {
    static int K,N;
    static int[] comb;
    static StringBuilder sb= new StringBuilder();
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        comb = new int[N];

        choose(0);
        System.out.print(sb);
    }


    public static void choose(int idx){
        if(idx==N){
            for(int a : comb){

                sb.append(a).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append('\n');

            return;
        }

        for(int i=1; i<=K; i++){
            comb[idx] = i;
            if(idx>=2 && comb[idx] == comb[idx-1] && comb[idx-2] == comb[idx-1]) continue;
            choose(idx+1);
        }
    }
}