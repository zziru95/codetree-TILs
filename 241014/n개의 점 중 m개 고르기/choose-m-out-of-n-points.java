import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[][] dots;
    static int[][] comb;
    static int minD = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dots = new int[n][2];
        comb = new int[m][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }
        choose(0,0);
        System.out.print(minD);

    }
    public static void choose(int start, int cnt){
        if(cnt ==m){
            calcD();
            return;
        }

        for(int i=start; i<n; i++){
            comb[cnt]= dots[i];
            choose(i+1, cnt+1);
        }

    }

    public static void calcD(){
        int Distance = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                if(i==j) continue;
                int newD = (comb[i][0]-comb[j][0]) * (comb[i][0]-comb[j][0]) + (comb[i][1]-comb[j][1])* (comb[i][1]-comb[j][1]);
                Distance = Math.max(Distance,newD); 
            }
        }

        minD = Math.min(minD,Distance);
    }
}