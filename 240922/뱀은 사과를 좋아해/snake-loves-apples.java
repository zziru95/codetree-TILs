import java.util.*;
import java.io.*;

public class Main {
    static class Snake{
        int r;
        int c;

        public Snake(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void move(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    static int N, M, K;
    static int[][] game;
    // 방향: 0 = 위, 1 = 아래, 2 = 왼쪽, 3 = 오른쪽
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} };
    static ArrayList<Snake> snake;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        snake = new ArrayList<>();
        snake.add(new Snake(0, 0));
        game = new int[N][N];
        game[0][0] = 2; // 초기 뱀 위치를 2로 표시
        int count = 0;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            game[r][c] = 1; // 1은 사과
        }

        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            int t = Integer.parseInt(st.nextToken());

            for(int j = 0; j < t; j++){
                if(headMove(com)){
                    count++;
                } else {
                    System.out.print(count);
                    return;
                }
            }
        }
        System.out.print(count);
    }


    public static boolean headMove(String com) {
        int d = -1;
        if(com.equals("U")) {
            d = 0;
        } else if(com.equals("D")) {
            d = 1;
        } else if(com.equals("L")) {
            d = 2;
        } else if(com.equals("R")) {
            d = 3;
        } else {
            // 잘못된 명령어인 경우
            return false;
        }

        int nr = snake.get(0).r + direction[d][0];
        int nc = snake.get(0).c + direction[d][1];

        // 그리드 내에 있는지 확인
        if(0 <= nr && nr < N && 0 <= nc && nc < N) {
            // 자기 자신과 충돌하는지 확인
            if(game[nr][nc] == 2){
                return false; // 자기 자신과 충돌
            }

            if(game[nr][nc] == 1) { // 사과를 먹은 경우
                Snake last = snake.get(snake.size()-1);
                int tempR = last.r;
                int tempC = last.c;

                for(int i = snake.size()-1; i >= 0; i--) {
                    Snake now = snake.get(i);
                    if(i == 0) {
                        now.move(nr, nc);
                        game[nr][nc] = 2; // 새로운 머리 위치를 2로 표시
                    } else {
                        Snake pre = snake.get(i-1);
                        now.move(pre.r, pre.c);
                    }
                }
                snake.add(new Snake(tempR, tempC));
                game[tempR][tempC] = 2;
                return true;
            } else if(game[nr][nc] == 0) { // 빈 칸인 경우
                Snake last = snake.get(snake.size()-1);
                int tempR = last.r;
                int tempC = last.c;
                game[tempR][tempC] = 0; // 꼬리 위치를 비움

                for(int i = snake.size()-1; i >= 0; i--) {
                    Snake now = snake.get(i);
                    if(i == 0) {
                        now.move(nr, nc);
                        game[nr][nc] = 2; // 새로운 머리 위치를 2로 표시
                    } else {
                        Snake pre = snake.get(i-1);
                        now.move(pre.r, pre.c);
                    }
                }
                return true;
            }
        }

        return false; // 벽과 충돌
    }
}