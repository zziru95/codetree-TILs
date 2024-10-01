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
                return this.num-o.num;
            }
            return this.w-o.w;
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

        for(int i=0; i<=t; i++) {
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
                    int nr = now.r + w * direction[d][0];
                    int nc = now.c + w * direction[d][1];

                    if(0<=nr && nr<n && 0<=nc && nc<n) {
                        nextBids[nr][nc].add(new Bid(nr,nc,w,d,now.num));
                    } else{
                        if (d == 0 || d == 2) {
                            int totalMovement = (Math.abs(nr) / (n - 1)) % 2;
                            if (totalMovement == 1) {
                                nr = (n - 1) - (Math.abs(nr) % (n - 1));  // 반사된 좌표
                            } else {
                                nr = Math.abs(nr) % (n - 1);  // 정상 좌표
                            }
                        }

                        // 우(1), 좌(3)의 경우 좌표 계산
                        if (d == 1 || d == 3) {
                            int totalMovement = (Math.abs(nc) / (n - 1)) % 2;
                            if (totalMovement == 1) {
                                nc = (n - 1) - (Math.abs(nc) % (n - 1));  // 반사된 좌표
                            } else {
                                nc = Math.abs(nc) % (n - 1);  // 정상 좌표
                            }
                        }

                        nextBids[nr][nc].add(new Bid(nr,nc,w,(d+2)%4,now.num));
                    }

                }
            }


        }
        int count = 0;
        for(int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                int temp = nextBids[i][j].size();
                if (temp > k) {
                    count += k;
                    while (nextBids[i][j].size() > k) {
                        nextBids[i][j].poll();
                    }
                } else {
                    count += temp;
                }
            }
        }
        
        bidArr = nextBids;
        return count;
    }
}