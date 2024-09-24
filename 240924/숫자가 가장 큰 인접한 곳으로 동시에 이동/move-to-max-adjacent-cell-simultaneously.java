import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t;
    static int[][] arr;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1}}; // 상, 하, 우, 좌

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 그리드 크기
        m = Integer.parseInt(st.nextToken()); // 초기 입자 수
        t = Integer.parseInt(st.nextToken()); // 시간 단계 수

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
            int r = Integer.parseInt(st.nextToken())-1; // 0-based 인덱스
            int c = Integer.parseInt(st.nextToken())-1;
            q.add(new int[] {r, c});
        }

        int count = m; // 초기 입자 수

        for(int timeStep=0; timeStep<t; timeStep++){
            int[][] temp = new int[n][n];
            int tempCnt = 0;

            // 현재 큐의 모든 입자 처리
            while(!q.isEmpty()) {
                int[] temp2 = q.poll();
                int r = temp2[0];
                int c = temp2[1];
                int maxValue = -2626262;
                int tempR = -1;
                int tempC = -1;

                // 인접한 셀 중 최대 값 찾기
                for(int d=0; d<4; d++){
                    int nr = r + direction[d][0];
                    int nc = c + direction[d][1];

                    if(nr >=0 && nr < n && nc >=0 && nc < n){
                        if(arr[nr][nc] > maxValue) {
                            maxValue = arr[nr][nc];
                            tempR = nr;
                            tempC = nc;
                        }
                    }
                }

                // 이동 가능 여부 확인
                if(arr[tempR][tempC] > arr[r][c]){
                    temp[tempR][tempC] += 1; // 이동
                }
                else{
                    temp[r][c] += 1; // 이동 불가, 현재 위치에 남음
                }
            }

            // temp 배열을 순회하며 조건에 맞는 셀을 큐에 다시 추가
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(temp[i][j] == 1) { // 정확히 한 입자가 도착한 경우
                        tempCnt++;
                        q.add(new int[] {i, j});
                    }
                    else if(temp[i][j] > 1){ // 여러 입자가 도착한 경우
                        // 문제의 요구사항에 따라 다르게 처리
                        // 예를 들어, 입자가 모두 소멸되거나, 복제될 수 있음
                        // 여기서는 소멸된다고 가정
                        // 소멸되므로 큐에 추가하지 않음
                        // 만약 복제된다면, tempCnt += temp[i][j]; q.add(new int[]{i,j}) 반복 추가 필요
                    }
                }
            }

            count = tempCnt; // 현재 시간 단계의 입자 수
        }

        System.out.print(count);
    }
}