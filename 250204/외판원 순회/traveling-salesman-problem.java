import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int INF = 1000000000; // 큰 값 설정
    static int[][] distance, memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        distance = new int[n][n];
        memo = new int[n][(1 << n)];

        // 거리 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(memo[i], -1);
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int minCost = tsp(0, 1); // 0번 도시에서 출발
        System.out.println(minCost == INF ? -1 : minCost); // 갈 수 없는 경우 -1 출력
    }

    public static int tsp(int current, int visited) {
        // 모든 도시 방문 완료 후 출발지(0)로 돌아가는 경우
        if (visited == (1 << n) - 1) {
            return (distance[current][0] != 0) ? distance[current][0] : INF; // 이동 불가면 INF 반환
        }

        // 이미 계산한 값이면 반환 (메모이제이션)
        if (memo[current][visited] != -1) {
            return memo[current][visited];
        }

        int minCost = INF;

        // 모든 도시를 탐색하며 방문하지 않은 도시 찾기
        for (int next = 0; next < n; next++) {
            if ((visited & (1 << next)) == 0 && distance[current][next] != 0) { // 이동 가능해야 함
                int newVisited = visited | (1 << next);
                int cost = distance[current][next] + tsp(next, newVisited);
                minCost = Math.min(minCost, cost);
            }
        }

        return memo[current][visited] = minCost;
    }
}
