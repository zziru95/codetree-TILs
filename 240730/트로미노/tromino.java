import java.util.*;
import java.io.*;
public class Main {
    //하,우,상,좌
    static int[][] direction= {{1,0},  {0,1}, {-1,0}, {0,-1}};
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][m];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Math.max(check1(graph,n,m), check2(graph,n,m));
        System.out.println(answer);


    }

    public static int check1(int[][] graph, int n, int m) {
        int maxValue = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if (i <= n - 3) { // 수직 합 계산
                    int temp1 = 0;
                    for (int k = 0; k < 3; k++) {
                        temp1 += graph[i + k][j];
                    }
                    maxValue = Math.max(maxValue, temp1);
                }
                if (j <= m - 3) { // 수평 합 계산
                    int temp2 = 0;
                    for (int k = 0; k < 3; k++) {
                        temp2 += graph[i][j + k];
                    }
                    maxValue = Math.max(maxValue, temp2);
                }

            }
        }
        return maxValue;
    }

    public static int check2(int[][] graph, int n, int m) {
        Queue<Node> q = new LinkedList<>();
        for(int i=0; i<n-1; i++) {
            for(int j=0; j<m-1; j++) {
                q.add(new Node(i,j));
            }
        }

        int maxValue =0;

        while(!q.isEmpty()) {
            Node now = q.poll();
            int r = now.row;
            int c = now.col;

            for(int d=0; d<4; d++) {
                int dr = r + direction[d][0];
                int dc = c + direction[d][1];
                if(0<=dr && dr<n && 0<= dc && dc<m) {
                    for(int e=0; e<4; e++) {
                        if(d%2 == e%2) continue;

                        int temp = graph[r][c] + graph[dr][dc];
                        
                        int er = dr + direction[e][0];
                        int ec = dc + direction[e][1];

                        if(0<=er && er<n && 0<= ec && ec<m) {
                            maxValue = Math.max(maxValue, (temp+graph[er][ec]));
                        }



                        int fr = r + direction[e][0];
                        int fc = c + direction[e][1];

                        if(0<=fr && fr<n && 0<= fc && fc<m) {
                            maxValue = Math.max(maxValue, (temp+graph[fr][fc]));
                        }

                }

                }


            }
        }


        return maxValue;
    }

    static class Node{
        int row;
        int col;
        public Node(int row, int col)  {
            this.row = row;
            this.col = col;
        }
    }
}