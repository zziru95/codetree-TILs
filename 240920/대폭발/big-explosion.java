import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int r;
        int c;
        int t;
        public Node(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }


    static int n, m, r, c;
    static int[][] arr;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} };


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        arr = new int[n][n];
        System.out.print(bomb());

    }

    public static int bomb(){
        int time = 1;
        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(r,c,time));
        arr[r][c] = 1;
        int cnt = 1;

        while(!q.isEmpty() && q.peek().t <= m) {
            Node now = q.poll();
            int nowR = now.r;
            int nowC = now.c;
            int nowT = now.t;
            for(int d=0; d<4; d++) {
                int nextR = nowR + direction[d][0] * (int) Math.pow(2,m-1);
                int nextC = nowC + direction[d][1] * (int) Math.pow(2,m-1);
                if( 0<= nextR && nextR < n && 0<= nextC && nextC < n && arr[nextR][nextC] == 0) {
                    arr[nextR][nextC] = 1;
                    q.add(new Node(nextR, nextC, nowT+1));
                    cnt++;
                }
            }

        }

        return cnt;
    }
}