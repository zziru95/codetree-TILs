import java.util.*;
import java.io.*;

public class Main {
    static int N,M,C;
    static int[][] graph;
    static int maxV = 0;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

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
                        find(i,j,n,m);
                    }
                }
            }
        }

        System.out.print(maxV);
    }


    public static void find(int r1, int c1, int r2, int c2){
        int v = 0;

        int w1 = 0;
        for(int i=c1; i<c1+M; i++){
            if(!valid(r1,i)) break;
            
            int w = graph[r1][i];
            
            if(C<w+w1) break;

            w1 += w;
            v += w*w;
        }

        int w2 = 0;
        for(int i=c2; i<c2+M; i++){
            if(!valid(r2,i)) break;

            int w = graph[r2][i];

            if(C<w+w2) break;

            w2 += w;
            v += w*w;

        }


        maxV = Math.max(v,maxV);
    }

    public static boolean valid(int r, int c){
        return 0<=r && r<N && 0<=c && c<N;
    }
}