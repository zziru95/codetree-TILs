import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int r;
        int c;
        int d;
        public Node(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    
    static int T,N,M;
    static int[][] arr;
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} }; //상하좌우
    static int[] change = {1,0,3,2};
    static Queue<Node> q;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int [N][N];
            q = new LinkedList<>();
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken())-1;
                int c = Integer.parseInt(st.nextToken())-1;
                String cmd = st.nextToken();
                int d = -1;
                if(cmd.equals("U")) d = 0;
                if(cmd.equals("D")) d = 1;
                if(cmd.equals("L")) d = 2;
                if(cmd.equals("R")) d = 3;
                q.add(new Node(r,c,d));
            }

            for(int i=0; i<10000; i++){
                move();
            }

            System.out.println(q.size());
        }
    }

    public static void move(){
        int[][] temp = new int [N][N];
        Node[][] temp2 = new Node [N][N];
        while(!q.isEmpty()) {
            Node now = q.poll();
            int d = now.d;
            int nr = now.r+ direction[d][0];
            int nc = now.c+ direction[d][1];
            if(nr<0 || N<=nr || nc<0 || N<=nc){
                d = change[d];
                nr = now.r;
                nc =now.c; 
            }
            temp[nr][nc] +=1;
            temp2[nr][nc] = new Node(nr,nc,d);
        }

        for(int i=0; i<N;i++){
            for(int j=0; j<N; j++){
                if(temp[i][j] ==1 ){
                    q.add(temp2[i][j]);
                }
            }
        }
    }

}