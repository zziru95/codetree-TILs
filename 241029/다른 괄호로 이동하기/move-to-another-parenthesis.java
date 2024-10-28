import java.util.*;
import java.io.*;


public class Main {

    static int N, A, B;
    static char[][] graph;
    static int maxTime = -1;
    static int[][] dist;
    static int INF = Integer.MAX_VALUE;
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} };




    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        graph = new char[N][N];
        dist = new int[N][N];

        for(int i=0; i<N; i++){
            String temp = br.readLine();
            for(int j=0; j<N; j++){
                graph[i][j] = temp.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                dijk(i,j);
            }
        }
        

        System.out.print(maxTime);



    }



    public static void dijk(int row, int col){
        int[] start =  {row,col,0};
        for(int i=0; i<N;i++){
            Arrays.fill(dist[i],INF);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> o1[2]-o2[2]);
        pq.add(start);
        dist[row][col] = 0;

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int r = now[0];
            int c = now[1];
            int w = now[2];

            if(dist[r][c]<w) continue;
            
            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(0<= nr &&  nr< N && 0<= nc && nc<N){
                    int newW = w;
                    if(graph[r][c] == graph[nr][nc]){
                        newW += A;
                    } else{
                        newW += B;
                    }
                    if(dist[nr][nc]>newW){
                        dist[nr][nc] = newW;
                        pq.add(new int[]{nr,nc,newW});
                    }
                }
                
            }


        }

        for(int i=0;i<N;i++){
            for(int j=0; j<N; j++){
                maxTime = Math.max(dist[i][j], maxTime);
            }
        }



    }
}