import java.util.*;
import java.io.*;

public class Main {
    static int N,M,C;
    static int[][] graph;
    static int maxV = 0;
    static int[] get;
    static int maxVal;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        get = new int[M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int n=i; n<N; n++){
                    for(int m=0; m<N; m++){
                        if(i==n && m<j+M) continue;
                        maxV = Math.max(maxV,(find(i,j) + find(n,m)));
                    }
                }
            }
        }

        System.out.print(maxV);
    }


    public static int find(int r, int c){
        int v = 0;

        int w1 = 0;
        int idx = 0;
        for(int i=c; i<c+M; i++){
            if(!valid(r,i)) break;
            
            get[idx] = graph[r][i];
            idx++;
        }

        maxVal = 0;
        findMaxSum(0,0,0);
        return maxVal;
    }
    
    public static void findMaxSum (int idx, int w, int v){
        if(w>C) return;

        if(idx ==M) {
            maxVal = Math.max(maxVal, v);
            return;
        }

        findMaxSum(idx+1, w,v);
        findMaxSum(idx+1, w+get[idx],v+ (get[idx] * get[idx]));
    }



    public static boolean valid(int r, int c){
        return 0<=r && r<N && 0<=c && c<N;
    }
}