import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t;
    static int[][] arr;
    // Directions: up, down, left, right
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 격자의 크기
        m = Integer.parseInt(st.nextToken()); // 구슬의 개수
        t = Integer.parseInt(st.nextToken()); // 이동 횟수

        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 0-based index
            int c = Integer.parseInt(st.nextToken()) - 1; // 0-based index
            q.add(new int[] {r, c});
        }

        for(int time=0; time<t; time++){
            int[][] temp = new int[n][n];
            while(!q.isEmpty()) {
                int[] bead = q.poll();
                int r = bead[0];
                int c = bead[1];
                int newR = r;
                int newC = c;
                int maxValue = -6;
                // 모든 방향을 검사하여  최대값을 가진 곳 방향을 찾음
                for(int d=0; d<4; d++){
                    int nr = r + direction[d][0];
                    int nc = c + direction[d][1];

                    if(nr >= 0 && nr < n && nc >= 0 && nc < n){
                        if(arr[nr][nc] > maxValue){
                            newR = nr;
                            newC = nc;
                            maxValue = arr[nr][nc];
                        }
                    }
                }

                temp[newR][newC] += 1;
            }


            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(temp[i][j] == 1) {
                        q.add(new int[] {i, j});
                    }
                    // temp[i][j] >=2 인 경우 충돌로 인해 모두 사라짐
                }
            }


        }

        System.out.print(q.size());
    }
}