import java.util.*;
import java.io.*;


public class Main {
    static class Node implements Comparable<Node>{
        int r,c,w;

        public Node(int r, int c ,int w){
            this.r = r;
            this.c = c;
            this.w = w;
        }

        public int compareTo(Node o){
            return this.w-o.w;
        }
    }

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
        Node start =  new Node(row,col,0);
        for(int i=0; i<N;i++){
            Arrays.fill(dist[i],INF);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);
        dist[row][col] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int r = now.r;
            int c = now.c;
            int w = now.w;

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
                        pq.add(new Node(nr,nc,newW));
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