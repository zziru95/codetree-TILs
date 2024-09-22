import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[][] game;
    // 방향: 0 = 위, 1 = 아래, 2 = 왼쪽, 3 = 오른쪽
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} };
    static ArrayDeque<int[]> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        game = new int[N][N];
        snake = new ArrayDeque<>();
        // 초기 뱀 위치
        snake.addFirst(new int[]{0, 0});
        game[0][0] = 2; // 뱀의 위치를 2로 표시

        // 사과 위치 입력
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            game[r][c] = 1; // 사과는 1로 표시
        }

        int count = 0; // 성공한 이동 횟수

        // 명령어 처리
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            int t = Integer.parseInt(st.nextToken());

            int dir = -1;
            switch(com){
                case "U":
                    dir = 0;
                    break;
                case "D":
                    dir = 1;
                    break;
                case "L":
                    dir = 2;
                    break;
                case "R":
                    dir = 3;
                    break;
                default:
                    // 잘못된 명령어인 경우 무시
                    break;
            }

            for(int j = 0; j < t; j++) {
                count++;
                if(!move(dir)){
                    System.out.print(count);
                    return;
                }
            }
        }
        System.out.print(count);
    }

    // 뱀의 이동을 처리하는 메서드
    public static boolean move(int dir){
        // 현재 머리 위치
        int[] head = snake.peekFirst();
        int nr = head[0] + direction[dir][0];
        int nc = head[1] + direction[dir][1];

        // 벽과 충돌 체크
        if(nr < 0 || nr >= N || nc < 0 || nc >= N){
            return false;
        }

        // 사과가 있는 경우
        if(game[nr][nc] == 1){
            // 충돌 체크 (사과가 있는 칸은 이미 뱀이 아니므로 생략 가능)
            // 새로운 머리 추가
            snake.addFirst(new int[]{nr, nc});
            game[nr][nc] = 2; // 뱀의 위치로 표시
        }
        else {
            // 빈 칸으로 이동: 꼬리를 제거하고 머리를 추가
            // 꼬리 제거
            int[] tail = snake.removeLast();
            game[tail[0]][tail[1]] = 0; // 꼬리 위치를 빈 칸으로 표시

            // 충돌 체크
            if(game[nr][nc] == 2){
                return false; // 몸통과 충돌
            }

            // 새로운 머리 추가
            snake.addFirst(new int[]{nr, nc});
            game[nr][nc] = 2; // 뱀의 위치로 표시
        }


        return true;
    }
}