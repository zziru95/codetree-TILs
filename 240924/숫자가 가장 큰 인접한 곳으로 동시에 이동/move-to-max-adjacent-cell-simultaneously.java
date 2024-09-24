import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t;
    static int[][] arr;
    // 상, 하, 좌, 우 순으로 우선순위를 설정
    static int[][] direction = { {-1,0}, {1,0}, {0,-1}, {0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        int count = m;
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<m;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            q.add(new int[] {r, c});
        }

        for(int time=0; time<t; time++){
            int[][] temp = new int [n][n];
            while(!q.isEmpty()) {
                int[] bead = q.poll();
                int r = bead[0];
                int c = bead[1];
                boolean moved = false;

                // 현재 위치의 값
                int currentValue = arr[r][c];
                int newR = r;
                int newC = c;

                // 우선순위에 따라 이동 가능한 첫 번째 방향을 찾음
                for(int d=0; d<4; d++){
                    int nr = r + direction[d][0];
                    int nc = c + direction[d][1];
                    if(nr >= 0 && nr < n && nc >= 0 && nc < n){
                        if(arr[nr][nc] > currentValue){
                            newR = nr;
                            newC = nc;
                            moved = true;
                            break; // 우선순위가 높은 방향을 찾았으므로 더 이상 탐색할 필요 없음
                        }
                    }
                }

                temp[newR][newC] += 1;
            }

            Queue<int[]> nextQ = new LinkedList<>();
            int tempCnt = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(temp[i][j] == 1) {
                        nextQ.add(new int[] {i, j});
                        tempCnt++;
                    }
                    // temp[i][j] >=2 일 경우 충돌로 모두 사라지므로 아무것도 하지 않음
                }
            }
            q = nextQ;
            count = tempCnt;
        }

        System.out.print(count);
    }
}