import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int r;
        int c;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }


    static int n, m, r, c;
    static int[][] arr;
    static int[][] direction = { {-1,0}, {1,0}, {0,1}, {0,-1} };


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        arr = new int[n][n];
        System.out.print(bomb());
    }

    public static int bomb(){
        ArrayList<Node> bombList = new ArrayList<>();
        bombList.add(new Node(r,c));
        arr[r][c] = 1;
        int cnt = 1;
        for(int time = 0; time<=m; time++) {
            ArrayList<Node> temp = new ArrayList<>();
            for(Node now : bombList) {
                    for(int d=0; d<4; d++) {
                        int nextR = now.r + direction[d][0] * (int) Math.pow(2,time-1);
                        int nextC = now.c + direction[d][1] * (int) Math.pow(2,time-1);
                        if( 0<= nextR && nextR < n && 0<= nextC && nextC < n && arr[nextR][nextC] == 0) {
                            arr[nextR][nextC] = 1;
                            temp.add(new Node(nextR, nextC));
                            cnt++;
                        }
                    }
                }
            bombList.addAll(temp);
            }
        return bombList.size();
        }

     
    


    public static void check(){
        StringBuilder sb =new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(arr[i][j]);
                if(j<n-1) {
                    sb.append(" ");
                }
            }
            if(i<n-1) {
                sb.append(System.lineSeparator());
            }
        }
        System.out.println(sb);
    }
}