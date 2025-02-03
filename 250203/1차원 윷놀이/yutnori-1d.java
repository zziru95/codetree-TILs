import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[] dice;
    static int globalScore = 0;
    // (idx, board상태) -> 해당 상태에서 도달한 최대 점수
    static HashMap<String, Integer> memo = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        dice = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        
        // 보드 배열의 길이는 k (원래 코드에서는 new int[m]로 되어 있었으나, 실제 반복문은 i < k)
        int[] board = new int[k]; 
        roll(0, 0, board);
        System.out.println(globalScore);
    }
    
    // idx: 현재 주사위 인덱스, currentScore: 지금까지 획득한 점수, board: 각 보드의 진행 상황
    public static void roll(int idx, int currentScore, int[] board) {
        // 전역 최대 점수 갱신
        globalScore = Math.max(globalScore, currentScore);
        
        // 모든 주사위를 사용했다면 종료
        if (idx == n) return;
        
        // 남은 주사위 횟수와 아직 끝나지 않은 보드의 수로 가능한 최대 추가 점수를 구함
        int unfinished = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != m) unfinished++;
        }
        // 각 주사위 당 최대 1점씩 얻을 수 있지만, 한 보드는 한 번만 점수를 얻으므로 추가 점수의 상한은 남은 주사위 횟수와 미완료 보드 개수 중 작은 값
        if (currentScore + Math.min(n - idx, unfinished) <= globalScore) return;
        
        // 메모이제이션: 현재 상태를 문자열로 표현 (idx와 board의 구성)
        String key = stateKey(idx, board);
        if (memo.containsKey(key) && memo.get(key) >= currentScore) {
            // 이전에 이 상태에서 더 높은 점수를 얻은 적이 있으므로 더 이상 진행할 필요 없음.
            return;
        }
        memo.put(key, currentScore);
        
        // 각 보드에 현재 주사위 값을 할당하는 경우를 모두 탐색
        for (int i = 0; i < board.length; i++) {
            if (board[i] == m) continue; // 이미 종료된 보드면 건너뜀.
            
            // 백트래킹: 현재 보드 상태를 임시로 저장
            int oldVal = board[i];
            int addedScore = 0;
            
            board[i] += dice[idx];  // 현재 주사위 값 더하기
            // 만약 보드 진행 상황이 m-1 이상이면 보드를 종료시키고 점수 추가
            if (board[i] >= m - 1) {
                board[i] = m;
                addedScore = 1;
            }
            
            roll(idx + 1, currentScore + addedScore, board);
            
            // 재귀 호출 후 원상복구
            board[i] = oldVal;
        }
    }
    
    // 상태를 문자열로 변환 (메모이제이션 key)
    private static String stateKey(int idx, int[] board) {
        StringBuilder sb = new StringBuilder();
        sb.append(idx).append("|");
        for (int i = 0; i < board.length; i++) {
            sb.append(board[i]).append(",");
        }
        return sb.toString();
    }
}
