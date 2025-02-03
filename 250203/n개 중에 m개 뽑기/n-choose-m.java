import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[] comb;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        comb = new int[M];

        choose(1,0);
        System.out.print(sb);

    }


    public static void choose(int start, int idx){
        if(idx == M){
            for(int a : comb){
                sb.append(a).append(" ");
            }
            // sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
            return;
        }

        for(int i=start; i<=N; i++){
            
            comb[idx] = i;
            choose(i+1,idx+1);
        }
    }
}