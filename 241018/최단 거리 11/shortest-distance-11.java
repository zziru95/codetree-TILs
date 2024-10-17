import java.util.*;

// 그래프의 최대 노드 수를 1005로 설정
public class Main {
    static int n, m;
    static int S, E;
    static List<Pair>[] graph = new ArrayList[1005]; // 그래프 인접 리스트
    static int[] d = new int[1005]; // 최소 비용 저장 배열
    static int[] pre = new int[1005]; // 이전 노드 추적용 배열

    // 우선순위 큐: {가중치, 노드} 순으로 오름차순 정렬
    static PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));

    // 간단한 가중치와 노드를 저장하기 위한 Pair 클래스
    static class Pair {
        int weight, node;

        Pair(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }
    }

    // 다익스트라 알고리즘 구현
    static void dijkstra(int start) {
        // 시작 노드의 최소 비용을 0으로 설정하고 큐에 추가
        d[start] = 0;
        pq.add(new Pair(0, start));

        while (!pq.isEmpty()) {
            Pair current = pq.poll(); // 큐에서 최솟값을 꺼냄
            int curWeight = current.weight;
            int curNode = current.node;

            // 이미 더 작은 비용이 있다면 무시
            if (d[curNode] != curWeight) {
                continue;
            }

            // 현재 노드에서 인접한 노드들을 탐색
            for (Pair neighbor : graph[curNode]) {
                int nxtWeight = neighbor.weight;
                int nxtNode = neighbor.node;

                int newWeight = curWeight + nxtWeight; // 새로운 비용 계산
                int beforeWeight = d[nxtNode]; // 이전에 기록된 비용

                // 비용이 더 작으면 갱신
                if (newWeight < beforeWeight) {
                    d[nxtNode] = newWeight;
                    pq.add(new Pair(newWeight, nxtNode));
                    pre[nxtNode] = curNode;
                } 
                // 비용이 같을 때, 더 작은 번호의 노드를 선택
                else if (newWeight == beforeWeight) {
                    if (pre[nxtNode] < curNode) {
                        d[nxtNode] = newWeight;
                        pq.add(new Pair(newWeight, nxtNode));
                        pre[nxtNode] = curNode;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 노드 수와 간선 수 입력
        n = sc.nextInt();
        m = sc.nextInt();

        // 그래프 초기화
        for (int i = 0; i < 1005; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int w = sc.nextInt();
            graph[s].add(new Pair(w, e)); // {가중치, 노드} 순
            graph[e].add(new Pair(w, s));
        }

        // 시작 노드와 종료 노드 입력
        S = sc.nextInt();
        E = sc.nextInt();

        // 최소 비용 배열 초기화 (INT_MAX로 설정)
        Arrays.fill(d, Integer.MAX_VALUE);

        // 다익스트라 알고리즘 실행
        dijkstra(S);

        // 최소 비용 출력
        System.out.println(d[E]);

        // 경로를 역추적하여 출력
        List<Integer> ans = new ArrayList<>();
        ans.add(E);
        int cur = E;
        while (cur != S) {
            int nxt = pre[cur];
            ans.add(nxt);
            cur = nxt;
        }

        // 경로를 올바른 순서로 출력
        Collections.reverse(ans);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}