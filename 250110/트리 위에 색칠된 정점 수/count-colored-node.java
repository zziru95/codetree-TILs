import java.util.*;
import java.io.*;

public class Main {
    static int n, k, q;
    static ArrayList<Integer>[] adj; // 무방향 인접 리스트
    static int[] parents, depths;
    static boolean[] colored, visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 입력받기 위한 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 노드 수
        n = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화 (무방향)
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        // n-1개의 간선을 무방향으로 저장
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 무방향으로 연결
            adj[a].add(b);
            adj[b].add(a);
        }

        // 부모/깊이/색칠/방문 배열 초기화
        parents = new int[n + 1];
        depths  = new int[n + 1];
        colored = new boolean[n + 1];
        visited = new boolean[n + 1];

        // 1번을 루트로 하여 트리 빌드 (BFS 또는 DFS)
        buildTree(1);

        // 색칠된 노드 개수 k
        k = Integer.parseInt(br.readLine());
        // k개의 노드를 입력받아 colored 체크
        for (int i = 0; i < k; i++) {
            int cNode = Integer.parseInt(br.readLine());
            colored[cNode] = true;
        }

        // 쿼리 개수 q
        q = Integer.parseInt(br.readLine());
        // 쿼리 처리
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            findColored(a, b);
        }

        // 결과 출력
        System.out.print(sb);
    }

    // BFS를 이용해 1번을 루트로 부모와 깊이를 세팅
    static void buildTree(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited[root] = true;
        depths[root] = 1;    // 루트의 깊이를 1로 설정
        parents[root] = 0;   // 루트의 부모는 없으므로 0(또는 -1)

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : adj[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    parents[next] = now;
                    depths[next] = depths[now] + 1;
                    queue.offer(next);
                }
            }
        }
    }

    // a ~ b 경로 상에 색칠된 노드가 몇 개인지 찾기
    static void findColored(int a, int b) {
        int count = 0;

        // 시작점들 먼저 체크
        if (colored[a]) count++;
        if (colored[b] && a != b) count++;

        // 깊이가 다르면, 더 깊은 쪽을 부모로 올리면서 체크
        while (depths[a] != depths[b]) {
            if (depths[a] > depths[b]) {
                a = parents[a];
                if (colored[a] && a !=b) count++;
            } else {
                b = parents[b];
                if (colored[b] && a!=b) count++;
            }
        }

        // 깊이가 같아진 뒤에도 둘이 다를 때까지 동시에 부모로 올림
        while (a != b) {
            a = parents[a];
            b = parents[b];
            if (colored[a]) count++;
            if (colored[b] && a!=b) count++;
        }

        sb.append(count).append('\n');
    }
}
