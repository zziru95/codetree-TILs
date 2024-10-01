import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t, k;
    static int[][] arr;
    static int[][] direction = { {1,0}, {0,1}, {-1,0}, {0,-1} }; //하 우 상 좌
    static PriorityQueue<Bid>[][] bidArr= new PriorityQueue[50][50];

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
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                bidArr[i][j] = new PriorityQueue<>();
            }
        }

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
            bidArr[r][c].add(new Bid(r,c,w,d,i));
        }

        int answer = m;

        for(int i=0; i<t; i++) {
            answer = move();
        }
        System.out.print(answer);

    }

    public static int move(){
        PriorityQueue<Bid>[][] nextBids = new PriorityQueue[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                nextBids[i][j] = new PriorityQueue<>();
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                while (!bidArr[i][j].isEmpty()) {
                    Bid now = bidArr[i][j].poll();
                    int d = now.d;
                    int w = now.w;
                    int nr = now.r;
                    int nc = now.c;

                    for (int step = 0; step < w; step++) {
                        nr += direction[d][0];
                        nc += direction[d][1];

                        // 벽에 부딪힐 때마다 방향을 반전시킴
                        if (nr < 0 || nr >= n) {
                            d = (d + 2) % 4;  // 상하 방향 반전
                            nr += 2 * direction[d][0];  // 반사되서 한 칸 뒤로 감
                        }
                        if (nc < 0 || nc >= n) {
                            d = (d + 2) % 4;  // 좌우 방향 반전
                            nc += 2 * direction[d][1];  // 반사되서 한 칸 뒤로 감
                        }
                    }


                    nextBids[nr][nc].add(new Bid(nr,nc,w,d,now.num));
                    

                }
            }


        }
        int count = 0;
        for(int i=0; i<n; i++){
            for (int j=0; j<n; j++){

                if(nextBids[i][j].size()>k) {
                    PriorityQueue<Bid> temp = new PriorityQueue<>();
                    for(int t=0; t<k; t++) {
                        temp.add(nextBids[i][j].poll());
                    }
                    nextBids[i][j] = temp;
                }

                count += nextBids[i][j].size();
            }
        }
        
        bidArr = nextBids;
        
        return count;
    }
}