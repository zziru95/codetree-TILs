import java.util.*;
import java.io.*;


public class Main {
    static class Node{
        int r,c,w;
        public Node(int r, int c, int w){
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
    static int n,k;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} };
    static int[][] arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        Node now = new Node(r,c,arr[r][c]);
        Node next = null;
        for(int i=0; i<k; i++){
            next = bfs(now);
            if(now.r == next.r && now.c == next.c) break;
            now = next;
           
            
        }

        System.out.print((next.r+1)+" "+(next.c+1));
    }

    public static Node bfs(Node now){
        Queue<Node> q = new ArrayDeque<>();
        visited = new boolean[n][n];
        visited[now.r][now.c] = true;
        q.add(now);
        int maxV = 0;
        int fr = now.r;
        int fc = now.c;
        int w = now.w;
        while(!q.isEmpty()){
            Node current = q.poll();
            int r = current.r;
            int c = current.c;
            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(0<= nr && nr< n && 0<= nc && nc< n){
                    if(!visited[nr][nc] && arr[nr][nc]<w){
                        visited[nr][nc] = true;
                        q.add(new Node(nr,nc,arr[nr][nc]));
                        if(arr[nr][nc] > maxV){
                            maxV = arr[nr][nc];
                            fr = nr;
                            fc = nc;
                        } else if(arr[nr][nc] == maxV){
                            if(nr < fr) {
                                fr = nr;
                                fc = nc;
                            } else if(nr == fr){
                                if(nc < fc){
                                    fc = nc;
                                }
                            }
                        }
                    }
                }
            }
        }
        return new Node(fr,fc,maxV);
    }
}