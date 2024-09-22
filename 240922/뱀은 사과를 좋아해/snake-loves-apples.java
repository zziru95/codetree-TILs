import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 그리드 크기
        int M = Integer.parseInt(st.nextToken()); // 사과 개수
        int K = Integer.parseInt(st.nextToken()); // 명령어 개수

        // 그리드 초기화
        int[][] game = new int[N][N];

        // 뱀 초기화
        ArrayDeque<int[]> snake = new ArrayDeque<>();
        snake.addFirst(new int[]{0,0});
        HashSet<Integer> occupied = new HashSet<>();
        occupied.add(0 * N + 0);
        game[0][0] = 2; // 초기 뱀 위치 표시

        // 사과 위치 입력
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1; // 0 기반 인덱스
            int c = Integer.parseInt(st.nextToken()) -1;
            game[r][c] =1; // 사과는 1로 표시
        }

        // 명령어 처리
        int count=0;
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            int t = Integer.parseInt(st.nextToken());

            // 명령어를 방향으로 매핑
            int dir = -1;
            switch(com){
                case "U":
                    dir=0;
                    break;
                case "D":
                    dir=1;
                    break;
                case "L":
                    dir=2;
                    break;
                case "R":
                    dir=3;
                    break;
            }

            // 방향 델타
            int[] dr = {-1,1,0,0};
            int[] dc = {0,0,-1,1};

            for(int j=0;j<t;j++){
                count++;
                // 현재 머리 위치
                int[] head = snake.peekFirst();
                int nr = head[0] + dr[dir];
                int nc = head[1] + dc[dir];

                // 그리드 경계 검사
                if(nr <0 || nr >=N || nc <0 || nc >=N){
                    System.out.print(count);
                    return;
                }

                int newPos = nr * N + nc;

                if(game[nr][nc]==1){
                    // 사과가 있는 경우
                    if(occupied.contains(newPos)){
                        // 이미 뱀의 몸통에 있는 경우 충돌
                        System.out.print(count);
                        return;
                    }
                    // 머리 추가
                    snake.addFirst(new int[]{nr,nc});
                    occupied.add(newPos);
                    game[nr][nc]=2; // 뱀의 위치 표시
                }
                else{
                    // 사과가 없는 경우: 꼬리 제거 후 이동
                    int[] tail = snake.removeLast();
                    int tailPos = tail[0] * N + tail[1];
                    occupied.remove(tailPos);
                    game[tail[0]][tail[1]] =0; // 꼬리 위치 비움

                    // 새로운 머리 위치가 뱀의 몸통에 있는지 검사
                    if(occupied.contains(newPos)){
                        System.out.print(count);
                        return;
                    }

                    // 머리 추가
                    snake.addFirst(new int[]{nr,nc});
                    occupied.add(newPos);
                    game[nr][nc]=2; // 뱀의 위치 표시
                }
            }
        }
        // 모든 이동이 성공적으로 완료된 경우
        System.out.print(count);
    }
}