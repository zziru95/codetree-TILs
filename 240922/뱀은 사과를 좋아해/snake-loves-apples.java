import java.util.*;
import java.io.*;

public class Main {
    static class Snake {
        int r;
        int c;

        public Snake(int r, int c) {
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
        switch(com){
            case "U":
                d = 0;
                break;
            case "D":
                d = 1;
                break;
            case "L":
                d = 2;
                break;
            case "R":
                d = 3;
                break;
            default:
                return false; // 잘못된 명령어
        }

        // 현재 머리 위치
        Snake head = snake.get(0);
        int nr = head.r + direction[d][0];
        int nc = head.c + direction[d][1];

        // 벽과 충돌하는지 확인
        if(nr < 0 || nr >= N || nc < 0 || nc >= N){
            return false; // 충돌
        }

        // 사과를 먹지 않고 이동할 경우
        if(game[nr][nc] == 0){
            // 꼬리를 제거
            Snake tail = snake.remove(snake.size()-1);
            game[tail.r][tail.c] = 0;

            // 새로운 머리 위치에 충돌하는지 확인
            if(game[nr][nc] == 2){
                return false; // 자기 자신과 충돌
            }

            // 머리를 추가
            snake.add(0, new Snake(nr, nc));
            game[nr][nc] = 2;
            return true;
        }
        // 사과를 먹는 경우
        else if(game[nr][nc] == 1){
            // 사과를 먹었으므로 꼬리를 제거하지 않고 머리를 추가
            snake.add(0, new Snake(nr, nc));
            game[nr][nc] = 2;
            return true;
        }
        // 자기 자신과 충돌
        else{
            return false;
        }
    }
}