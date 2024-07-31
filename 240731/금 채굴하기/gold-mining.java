import java.util.*;
import java.io.*;

public class Main {
    static int[][] direction= {{1,0},{-1,0},{0,1},{0,-1}};
    static int[] rewards = new int[2];
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                boolean[][] visited = new boolean[n][n];
                bfs(graph,n,m,i,j, visited);
            }
        }

        System.out.println(rewards[0]+1);

    }

    public static void bfs(int[][] graph, int n, int m, int row, int col, boolean[][] visited) {
        int qCnt = 1;
        int goldCnt = (graph[row][col] == 1) ? 1 : 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row,col));
        visited[row][col] = true;

        for(int i=0; i<n; i++){
            int cost = (i*i)+(i+1)*(i+1);
            int income = m * goldCnt - cost;
            if(income > rewards[1]) {
                rewards[0] = i+1;
                rewards[1] = income;
            }
            int temp = 0;
            while(qCnt>0) {
                Node now = q.poll();
                qCnt--;
                int r = now.row;
                int c = now.col;
                
                for(int d=0; d<4; d++) {
                    int nr = r+direction[d][0];
                    int nc = c+direction[d][1];

                    if(0<=nr&&nr<n&&0<=nc&&nc<n&&!visited[nr][nc]) {
                        if(graph[nr][nc] == 1) goldCnt++;
                        temp++;
                        visited[nr][nc] = true;
                        q.add(new Node(nr,nc));
                    }
                }

            }

            qCnt = temp;

        }

        int i = n-1;
        int cost = (i*i)+(i+1)*(i+1);
        int income = m * goldCnt - cost;


        if(income > rewards[1]) {
                rewards[0] = i+1;
                rewards[1] = income;
            }
    }

    static class Node {
        int row;
        int col;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}