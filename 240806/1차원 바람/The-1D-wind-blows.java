import java.util.*;
import java.io.*;

public class Main {
    static int n, m, q;
    static int[][] building;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        building = new int[n][m];
        

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                building[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<q;i++) {
            st = new StringTokenizer(br.readLine());
            visited = new boolean[n];
            int r = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            visited[r-1] = true;
            wind(d, r-1);
        }

        for (int[] row : building) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void wind(String d, int row) {
        if (d.equals("L")) {
            int temp = building[row][m-1];
            for(int i=0; i<m-1;i++){
                building[row][m-1-i] = building[row][m-i-2];
            }
            building[row][0] = temp;
            if(row !=0) {
                if(checkSame(row, row-1)) {
                    wind("R", row-1);
                }
            }

            if(row != n-1) {
                if(checkSame(row, row+1)) {
                    wind("R", row+1);
                }
            }
        }
        if (d.equals("R")) {
            int temp = building[row][0];
            for(int i=0; i<m-1;i++){
                building[row][i] = building[row][i+1];
            }
            building[row][m-1] = temp;
            if(row !=0) {
                if(checkSame(row, row-1)) {
                    wind("L", row-1);
                }
            }

            if(row != n-1) {
                if(checkSame(row, row+1)) {
                    wind("L", row+1);
                }
            }
        }
    }

    public static boolean checkSame(int sr, int nr) {
        if (visited[nr]) return false;

        for (int i = 0; i < m; i++) {
            if (building[sr][i] == building[nr][i]) {
                visited[nr] = true;
                return true;
            }
        }

        return false;
    }
}