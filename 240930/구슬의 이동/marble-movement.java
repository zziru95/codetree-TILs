import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t, k;
    static int[][] arr;
    static int[][] direction = { {1,0}, {0,1}, {-1,0}, {0,-1} }; //하 우 상 좌
    static Queue<Bid> q;

    static class Bid implements Comparable<Bid>{
        int r;
        int c;
        int w;
        int d;
        int num;

        public Bid(int r, int c, int w, int d, int num){
            this.r = r;
            this.c = c;
            this.w = w;
            this.d = d;
            this.num = num;
        }

        @Override
        public int compareTo(Bid o) {
            if(this.w == o.w) {
                return o.num-this.num;
            }
            return o.w-this.w;
        }
    }


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        q = new LinkedList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            char D = st.nextToken().charAt(0);
            int w = Integer.parseInt(st.nextToken());
            arr[r][c] = 1;
            int d = 0;
            if(D == 'U') {
                d = 2;
            } else if(D == 'D') {
                d = 0;
            } else if(D == 'L') {
                d = 3;
            } else if(D == 'R') {
                d= 1;
            }
            q.add(new Bid(r,c,w,d,i));
        }

        int answer = m;

        for(int i=0; i<t; i++) {
            answer = move();
        }

    }

    public static int move(){
        int[][] nextArr = new int[n][n];
        Queue<Bid> nextBids = new LinkedList<>();

        while(!q.isEmpty()) {
            Bid now = q.poll();
            int d = now.d;
            int w = now.w;
            int nr = now.r + w * direction[d][0];
            int nc = now.c + w * direction[d][1];

            if(0<=nr && nr<n && 0<=nc && nc<n) {
                nextArr[nr][nc]++;
                nextBids.add(new Bid(nr,nc,w,d,now.num));
            } else{
                 //하 우 상 좌  
                if(d==0) {
                    int temp = (nr + w) / (n-1) //벽에 맞는 횟수
                    if(temp % 2 == 1) {
                        nr = (n-1) - ((nr+ w) % (n-1))
                    } else {
                        nr = (nr+w) % (n-1)
                    }
                } else if(d==1){
                    int temp = (nc + w) / (n-1)
                    if(temp % 2 == 1) {
                        nc = (n-1) - ((nc+ w) % (n-1))
                    } else {
                        nc = (nc+w) % (n-1)
                    }
                } else if(d==2) {
                    int temp = ((n-1)-nr+w)  / (n-1) //벽에 맞는 횟수
                    if(temp % 2 == 1) {
                        nr = (n-1) - ((nr+ w) % (n-1))
                    } else {
                        nr = (nr+w) % (n-1)
                    }

                }

            }

        }
    }
}