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
    
    static int N,M,K;
    static int[][] game;
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} }; //
    static ArrayList<Snake> snake;


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        snake = new ArrayList<>();
        snake.add(new Snake(0,0));
        game = new int [N][N];
        game[0][0] = 2;
        int count = 0;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            game[r][c] = 1;
        }

        for (int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            int t = Integer.parseInt(st.nextToken());

            for( int j=0; j<t; j++){
                count++;
                if(!headMove(com)){
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
            d= 0;
        } else if(com.equals("D")) {
            d=1;
        } else if(com.equals("L")) {
            d=2;
        } else if(com.equals("R")) {
            d=3;
        }
        int nr = snake.get(0).r + direction[d][0];
        int nc = snake.get(0).c + direction[d][1];




        if(0<= nr && nr<N && 0<= nc && nc< N) {
            if(game[nr][nc] == 0) {
                Snake last = snake.get(snake.size()-1);
                int tempR = last.r;
                int tempC = last.c;
                game[tempR][tempC] = 0;
                for(int i=snake.size()-1; i>=0; i--) {
                    Snake now = snake.get(i);
                    if(i==0) {
                        now.move(nr,nc);
                        game[nr][nc] = 2;
                    } else {
                        Snake pre = snake.get(i-1);
                        now.move(pre.r,pre.c);
                    }
                }
                return true;
            } else if(game[nr][nc] == 1) {
                Snake last = snake.get(snake.size()-1);
                int tempR = last.r;
                int tempC = last.c;

                for(int i=snake.size()-1; i>=0; i--) {
                    Snake now = snake.get(i);
                    if(i==0) {
                        now.move(nr,nc);
                        game[nr][nc] = 2;
                    } else {
                        Snake pre = snake.get(i-1);
                        now.move(pre.r,pre.c);
                    }
                }
                snake.add(new Snake(tempR,tempC));
                return true;
            } 
        }
    
    
    return false;

    }


}