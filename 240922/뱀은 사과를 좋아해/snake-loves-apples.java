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
    // Directions: 0 = Up, 1 = Down, 2 = Left, 3 = Right
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} };
    static LinkedList<Snake> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        game = new int[N][N];
        snake = new LinkedList<>();
        snake.addFirst(new Snake(0, 0));
        game[0][0] = 2; // Mark initial position

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;
            game[r][c] = 1; // 1 represents apple
        }

        int count =0;

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            int t = Integer.parseInt(st.nextToken());

            int dir = -1;
            switch(com){
                case "U":
                    dir =0;
                    break;
                case "D":
                    dir =1;
                    break;
                case "L":
                    dir =2;
                    break;
                case "R":
                    dir =3;
                    break;
                default:
                    // Invalid command
                    break;
            }

            for(int j=0; j<t; j++) {
                count++;
                if(!headMove(dir)){
                    System.out.print(count);
                    return;
                }
            }
        }
        System.out.print(count);
    }

    public static boolean headMove(int dir){
        Snake head = snake.getFirst();
        int nr = head.r + direction[dir][0];
        int nc = head.c + direction[dir][1];

        // Check wall collision
        if(nr <0 || nr >=N || nc <0 || nc >=N){
            return false;
        }

        // If moving to apple
        if(game[nr][nc] ==1){
            // Check collision with body
            if(game[nr][nc] ==2){
                return false;
            }
            // Add new head
            snake.addFirst(new Snake(nr, nc));
            game[nr][nc] =2;
        }
        else{
            // Moving to empty: remove tail first
            Snake tail = snake.removeLast();
            game[tail.r][tail.c] =0;

            // Check collision with body
            if(game[nr][nc] ==2){
                return false;
            }

            // Add new head
            snake.addFirst(new Snake(nr, nc));
            game[nr][nc] =2;
        }

        return true;
    }
}