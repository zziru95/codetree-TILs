import java.util.*;
import java.io.*;


public class Main {
    static int n,m,r,c;
    static int[][] board;
    static int[][] direction = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 좌 하 우 상

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken())-1;
        c = Integer.parseInt(stringTokenizer.nextToken())-1;
        String order = br.readLine().trim();
        char[] orders = order.toCharArray();
        board = new int[n][n];
        board[r][c] = 6;
        Dice dice = new Dice(1, 6, 4, 3, 2, 5);



        for (char ord : orders) {
            int nr = r;
            int nc = c;

            if (ord == 'L') {
                nr = r + direction[0][0];
                nc = c + direction[0][1];

                if (!check(nr,nc)) continue;
                dice.rollLeft();
            }

            if (ord == 'R') {
                nr = r + direction[2][0];
                nc = c + direction[2][1];

                if (!check(nr,nc)) continue;
                dice.rollRight();
            }

            if (ord == 'U') {
                nr = r + direction[3][0];
                nc = c + direction[3][1];

                if (!check(nr,nc)) continue;
                dice.rollBackward();
            }

            if (ord == 'D') {
                nr = r + direction[1][0];
                nc = c + direction[1][1];

                if (!check(nr,nc)) continue;
                dice.rollForward();
            }
            board[nr][nc] = dice.bottom;
            r = nr;
            c = nc;
        }


        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += board[i][j];
            }
        }

        System.out.println(answer);


    }

    public static boolean check(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < n;
    }

    static class Dice {
        int top;
        int bottom;
        int left;
        int right;
        int front;
        int back;

        public Dice(int top, int bottom, int left, int right, int front, int back) {
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
            this.front = front;
            this.back = back;
        }

        public void rollLeft() {
            // 왼쪽으로 굴릴 때의 면 변환을 임시 변수로 처리
            int tempTop = top;
            top = right;
            right = bottom;
            bottom = left;
            left = tempTop;
        }

        public void rollRight() {
            // 오른쪽으로 굴릴 때의 면 변환을 임시 변수로 처리
            int tempTop = top;
            top = left;
            left = bottom;
            bottom = right;
            right = tempTop;
        }

        public void rollForward() {
            // 앞으로 굴릴 때의 면 변환을 임시 변수로 처리
            int tempTop = top;
            top = back;
            back = bottom;
            bottom = front;
            front = tempTop;
        }

        public void rollBackward() {
            // 뒤로 굴릴 때의 면 변환을 임시 변수로 처리
            int tempTop = top;
            top = front;
            front = bottom;
            bottom = back;
            back = tempTop;
        }
    }
}