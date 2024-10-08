import java.util.*;
import java.io.*;


public class Main {
    static class Bid{
        int r,c,d,w,num;

        public Bid(int r, int c, int d, int w, int num) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.w = w;
            this.num = num;
        }
    }

    static int n,m,t;
    static Bid[][] arr;
    static Bid[][] nextArr;
    static Set<int[]> bids;

    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[] change = {1,0,3,2};

    static int maxW;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        maxW = 0;
        arr = new Bid[n][n];
        bids = new HashSet<>();
        int cnt = 0;

        //입력값받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            char D = st.nextToken().charAt(0);
            int w = Integer.parseInt(st.nextToken());
            int d = -1;
            if(D=='U') d=0;
            if(D=='D') d=1;
            if(D=='L') d=2;
            if(D=='R') d=3;
            bids.add(new int[]{r, c});
            arr[r][c] =new Bid(r, c, d, w, i);
        }
        //t번실행하기
        for (int i = 0; i < t; i++) {
            cnt = move();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt);
        sb.append(" ");
        sb.append(maxW);
        System.out.print(sb);

    }

    public static int move() {
        Set<int[]> nextBids = new HashSet<>();
        nextArr = new Bid[n][n];
        int cnt = 0;
        for (int[] now : bids) {
            //지금 구슬 가져오기
            Bid nowBids = arr[now[0]][now[1]];
            int d = nowBids.d;
            int w = nowBids.w;
            int num = nowBids.num;
            int nr = nowBids.r + direction[d][0];
            int nc = nowBids.c + direction[d][1];
            //범위밖이면 방향만바꾸기
            if (!(0 <= nr && nr < n && 0 <= nc && nc < n)) {
                nr = nowBids.r;
                nc = nowBids.c;
                d = change[d];
            }
            //다음에 갈 곳에 아무것도 없으면 들어가기
            if (nextArr[nr][nc] == null) {
                nextArr[nr][nc] = new Bid(nr,nc,d,w,num);
                nextBids.add(new int[]{nr, nc});
                cnt++;
            } else {
                //있으면 꺼내와서 지금꺼랑 비교하기
                Bid another = nextArr[nr][nc];
                w = another.w + w;
                //안에있는친구가 더 크면 안에있는 친구로 합체
                if (another.num > num) {
                    nextArr[nr][nc] = new Bid(nr, nc, another.d, w, another.num);
                } else {
                    //지금것이 더 크면 지금껄로 합체
                    nextArr[nr][nc] = new Bid(nr, nc, d, w, num);
                }
            }
            //최대로 무거운거 바꿔주기
            maxW = Math.max(maxW, w);
        }
        bids = nextBids;
        arr = nextArr;
        return cnt;
    }
}