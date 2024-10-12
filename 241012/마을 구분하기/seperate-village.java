import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} };
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        arr = new int [n][n];
        visited = new boolean [n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt =0;
        ArrayList<Integer> people = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j] ==1 && !visited[i][j] ){
                    cnt++;
                    visited[i][j] = true;
                    people.add(dfs(i,j));
                }
            }
        }
        Collections.sort(people);
        StringBuilder sb =new StringBuilder();
        sb.append(cnt).append(System.lineSeparator());
        for(int a: people) {
            sb.append(a).append(System.lineSeparator());
        }
        System.out.print(sb);
    }

    public static int dfs(int r, int c){
        int cnt = 1;

        for(int d=0; d<4; d++){
            int nr = r+ direction[d][0];
            int nc = c+ direction[d][1];
            if(check(nr,nc) && !visited[nr][nc] && arr[nr][nc] == 1){
                visited[nr][nc] = true;
                cnt += dfs(nr,nc);
            }
        }

        return cnt;

    }

    public static boolean check(int r, int c){
        return 0<=r && r<n && 0<=c && c<n;
    }
}