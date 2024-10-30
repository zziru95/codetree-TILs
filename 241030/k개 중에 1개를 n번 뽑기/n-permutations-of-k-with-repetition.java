import java.util.*;
import java.io.*;


public class Main {
    static int K,N;
    static int[] comb;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        comb= new int[N];
        choose(0);

    }


    public static void choose( int cnt){

        if(cnt == N){
            StringBuilder sb = new StringBuilder();
            for(int a: comb){
                sb.append(a).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
            return;
        }


        for(int i=1; i<=K;i++){
            comb[cnt] = i;
            choose(cnt+1);
        }
    }
}