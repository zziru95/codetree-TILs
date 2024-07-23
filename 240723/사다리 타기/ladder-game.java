import java.util.*;
import java.io.*;
public class Main {
    static class Edge{
        int col;
        int row;
        public Edge(int col, int row){
            this.col = col;
            this.row = row;
        }
    }
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }


        int[][] ladder = new int[17][n+2];
        for(int i=0; i<17; i++){
            ladder[i][0] = -1;
            ladder[i][n+1] = -1;
        }

        int answer = m;

        int[] result = play(ladder, edges, n);

        for (int i=0; i<m; i++){
            if (answer <= i) {
                break;
            }
            List<List<Edge>> temp = combination(edges,i);
            for (List<Edge> edgeList : temp) {
                int[] nowResult = play(ladder,edgeList,n);
                if (Arrays.equals(nowResult, result)) {
                    answer = i;
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    public static int[] play(int[][] ladder, List<Edge> edges, int n){
        int[] gameResult = new int[n];
        //사다리 게임시작 사다리 세팅
        for (Edge edge : edges) {
            int nc = edge.col;
            int nr = edge.row;
            ladder[nr][nc] = 1;
            ladder[nr][nc+1] = 1;
        }

        for (int i=1; i<=n ; i++){
            int nr = 1;
            int nc = i;

            while (nr < 17){
                if (ladder[nr][nc] == 0){
                    nr++;
                } else {
                    if(ladder[nr][nc+1] == 1) {
                        nr++;
                        nc++;
                    } else {
                        nr++;
                        nc--;
                    }
                }
            }
            gameResult[i-1] = nc;
        }



        //사다리 게임 끝나고 되돌리기
        for (Edge edge : edges) {
            int nc = edge.col;
            int nr = edge.row;
            ladder[nr][nc] = 0;
            ladder[nr][nc+1] = 0;
        }

        return gameResult;
    }

    public static List<List<Edge>> combination(List<Edge> edges, int count) {
        List<List<Edge>> result = new ArrayList<>();
        combinationHelper(edges, new ArrayList<>(), result, count, 0);
        return result;

    }

    public static void combinationHelper(List<Edge> edges, List<Edge> current, List<List<Edge>> result, int count, int start){
        if (count ==0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < edges.size(); i++) {
            current.add(edges.get(i));
            combinationHelper(edges, current, result, count - 1, i + 1);
            current.remove(current.size() - 1);
        }

    }
}