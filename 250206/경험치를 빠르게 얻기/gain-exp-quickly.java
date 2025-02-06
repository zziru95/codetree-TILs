import java.util.*;
import java.io.*;

public class Main {
    static final int INF = Integer.MAX_VALUE; // 매우 큰 값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력: n은 퀘스트의 개수, m은 목표 경험치
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] quests = new int[n][2];
        int totalExp = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // quests[i][0] : 해당 퀘스트가 주는 경험치
            // quests[i][1] : 해당 퀘스트 수행에 걸리는 시간
            quests[i][0] = Integer.parseInt(st.nextToken());
            quests[i][1] = Integer.parseInt(st.nextToken());
            totalExp += quests[i][0];
        }
        
        // 모든 퀘스트를 다 수행해도 목표 경험치를 달성할 수 없으면 -1 출력
        if (totalExp < m) {
            System.out.println(-1);
            return;
        }
        
        // dp[i] : 경험치 i를 달성하는 데 필요한 최소 시간
        // 목표는 dp[m] (경험치가 m 이상인 경우, dp[m]에 값을 저장)
        int[] dp = new int[m + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0; // 경험치 0은 0 시간
        
        // 각 퀘스트에 대해 0/1 Knapsack 방식으로 dp 갱신
        // (각 퀘스트는 한 번만 사용할 수 있음)
        for (int[] quest : quests) {
            int exp = quest[0];
            int time = quest[1];
            // 역순으로 순회하여 같은 퀘스트가 중복 선택되지 않도록 함
            for (int currentExp = m - 1; currentExp >= 0; currentExp--) {
                // 현재 경험치를 달성하는 것이 가능할 때
                if (dp[currentExp] != INF) {
                    // 새로운 경험치는 m을 넘지 않도록 clamping
                    int newExp = Math.min(m, currentExp + exp);
                    // 새로운 경험치를 달성하는 최소 시간 갱신
                    dp[newExp] = Math.min(dp[newExp], dp[currentExp] + time);
                }
            }
        }
        
        // dp[m]에 목표 경험치 m 이상을 달성하는 최소 시간이 저장됨
        System.out.println(dp[m]);
    }
}
