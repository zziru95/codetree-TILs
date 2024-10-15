import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[][] arr;
    static int[][] direction = { {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
    static int[] start = new int[2];
    static int[] end = new int[2];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken()) - 1;
        start[1] = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        end[0] = Integer.parseInt(st.nextToken()) - 1;
        end[1] = Integer.parseInt(st.nextToken()) - 1;
        
        int result = bfs();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    public static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][n][k + 1];  // 좌표 + 남은 벽 제거 횟수
        
        q.add(new int[] {start[0], start[1], 0, k});  // {r, c, 이동 거리, 남은 벽 제거 횟수}
        visited[start[0]][start[1]][k] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int dist = current[2];
            int remainingWalls = current[3];
            
            if (r == end[0] && c == end[1]) {
                return dist;  // 도착점에 도달하면 거리 반환
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (arr[nr][nc] == 0 && !visited[nr][nc][remainingWalls]) {
                        // 빈 공간으로 이동
                        visited[nr][nc][remainingWalls] = true;
                        q.add(new int[] {nr, nc, dist + 1, remainingWalls});
                    } else if (arr[nr][nc] == 1 && remainingWalls > 0 && !visited[nr][nc][remainingWalls - 1]) {
                        // 벽이지만 벽을 지나갈 수 있는 경우
                        visited[nr][nc][remainingWalls - 1] = true;
                        q.add(new int[] {nr, nc, dist + 1, remainingWalls - 1});
                    }
                }
            }
        }
        
        return Integer.MAX_VALUE;  // 도착할 수 없으면 -1로 처리
    }
}