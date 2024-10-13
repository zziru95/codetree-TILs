import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[M];
        comb(1,0);
    }

    public static void comb(int current, int cnt){
        if(cnt == M){
            StringBuilder sb =new StringBuilder();
            for(int i=0;i<M;i++){
                sb.append(answer[i]);
                if(i<M-1) sb.append(" ");
            }
            System.out.println(sb);
            return;
        }


        for(int i= current; i<=N; i++){
            int temp = answer[cnt];
            answer[cnt]=i;
            comb(i+1,cnt+1);
            answer[cnt] = temp;
        }
    }
}