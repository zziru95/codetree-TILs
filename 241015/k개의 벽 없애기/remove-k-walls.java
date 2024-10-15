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
        int[][] visited = new int[n][n];  // 벽을 넘은 횟수 기록
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1);  // -1로 초기화해서 방문하지 않음을 표시
        }

        q.add(new int[]{start[0], start[1], 0, 0});  // {r, c, 이동 거리, 넘은 벽 개수}
        visited[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int dist = current[2];
            int wallsBroken = current[3];
            
            if (r == end[0] && c == end[1]) {
                return dist;  // 도착점에 도달하면 거리 반환
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (arr[nr][nc] == 0 && (visited[nr][nc] == -1 || visited[nr][nc] > wallsBroken)) {
                        // 빈 공간으로 이동하거나 벽을 더 적게 넘어서 도착한 경우
                        visited[nr][nc] = wallsBroken;
                        q.add(new int[]{nr, nc, dist + 1, wallsBroken});
                    } else if (arr[nr][nc] == 1 && wallsBroken < k && (visited[nr][nc] == -1 || visited[nr][nc] > wallsBroken + 1)) {
                        // 벽이지만 벽을 넘을 수 있는 경우
                        visited[nr][nc] = wallsBroken + 1;
                        q.add(new int[]{nr, nc, dist + 1, wallsBroken + 1});
                    }
                }
            }
        }
        
        return Integer.MAX_VALUE;  // 도착할 수 없으면 -1로 처리
    }
}