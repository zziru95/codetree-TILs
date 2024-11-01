import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static boolean[] visited = new boolean[1001];
    static int[][] lines;
    static int maxCnt = 0;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        lines = new int[n][2];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        choose(0, 0);

        System.out.print(maxCnt);
    }

    public static void choose(int idx, int cnt){
        if(idx == n){
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        ArrayList<Integer> temp = new ArrayList<>();
        int[] now = lines[idx];
        boolean flag = true;

        for(int i = now[0]; i <= now[1]; i++){
            if(visited[i]) {
                flag = false;
                break;
            }
            temp.add(i);
            visited[i] = true;
        }

        if(flag){
            choose(idx + 1, cnt +1);
        }

        // 방문 해제 (백트래킹)
        for(int back : temp){
            visited[back] = false;
        }

        // 구간을 선택하지 않는 경우도 탐색
        choose(idx + 1, cnt);
    }
}