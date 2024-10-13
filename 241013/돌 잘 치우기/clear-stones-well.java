import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int r,c,t;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int n,k,m;
    static int[][] direction = { {-1,0},{1,0},{0,1},{0,-1} };
    static int[][] arr;
    static boolean[][] visited; 
    static int maxV;
    static Queue<Node> q;
    static ArrayList<Node> comb;
    static ArrayList<ArrayList<Node>> result;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        maxV = 0;
        comb = new ArrayList<>();
        result = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if( arr[i][j] ==1) {
                    comb.add(new Node(i,j));
                }
            }
        }
        q = new ArrayDeque<>();

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            q.add(new Node(r,c));
        }

        combination(new ArrayList<>(), 0);

        for(ArrayList<Node> now : result){
            for(Node temp : now){
                arr[temp.r][temp.c] = 0;
            }
            bfs();

            for(Node temp : now){
                arr[temp.r][temp.c] = 1;
              
            }
        }

        System.out.print(maxV);
    }


    public static void combination(List<Node> current, int start){
        if(current.size() == m){
            result.add(new ArrayList<>(current));
            return;
        }

        if(comb.size()-start < m- current.size()) return;
        
        for(int i=start; i<comb.size(); i++){
            current.add(comb.get(i));
            combination(current,i+1);
            current.remove(current.size()-1);
        }

        return;
    } 

    public static void bfs(){
        Queue<Node> que = new ArrayDeque<>(q);
        visited = new boolean[n][n];
        for(Node temp : que){
            visited[temp.r][temp.c] = true;
        }
        int cnt =0;

        while(!que.isEmpty()){
            Node current = que.poll();
            int r = current.r;
            int c= current.c;
            cnt++;
            for(int d=0; d<4; d++){
                int nr = r + direction[d][0];
                int nc = c + direction[d][1];
                if(0<= nr && nr< n && 0<= nc && nc< n && arr[nr][nc]==0 &&!visited[nr][nc]){
                    visited[nr][nc] = true;
                    que.add(new Node(nr,nc));
                }
            }
        }


        maxV = Math.max(maxV, cnt);
    }

}