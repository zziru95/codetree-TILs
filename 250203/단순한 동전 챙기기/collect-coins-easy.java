import java.util.*;
import java.io.*;

public class Main {
    static class Edge{
        int r,c;
        public Edge(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int answer = Integer.MAX_VALUE;
    static int n;
    static int[] S,E;
    static char[][] graph;
    static int[][] dist;
    static Edge[] location;
    static ArrayList<Integer> nums;
    static int[] comb = new int[3];
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new char[n][n];
        dist = new int[10][10];
        location = new Edge[10];
        nums = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            graph[i] = br.readLine().toCharArray();
            for(int j=0; j<n; j++){
                if(graph[i][j] == 'S') {
                    S = new int[] {i,j};
                } else if(graph[i][j] == 'E'){
                    E = new int[] {i,j};
                } else if(Character.isDigit(graph[i][j])){
                    int num = graph[i][j] - '0';
                    location[num] = new Edge(i,j);
                    nums.add(num);
                }
            }
        }
        if(nums.size()>=3){
            Collections.sort(nums);
            setting();
            choose(1,0);
        } else{
            answer = -1;
        }

        System.out.print(answer);

        


    }

    public static void setting(){
        for(int i=0; i<nums.size(); i++){
            int a = nums.get(i);
            for(int j=i+1; j<nums.size(); j++){
                int b = nums.get(j);
                Edge first = location[a];
                Edge second = location[b];
                int d = Math.abs(first.r-second.r) + Math.abs(first.c-second.c); 
                dist[a][b] = d;
            }
        }
    }

    public static void choose(int start, int idx){
        if(idx ==3){
            Edge curr = location[comb[0]];
            Edge next = null;
            int totalD = Math.abs(S[0]-curr.r) + Math.abs(S[1]-curr.c);
            for(int i=1; i<=2; i++){
                next = location[comb[i]];
                int nowD = Math.abs(curr.r-next.r) + Math.abs(curr.c-next.c);
                totalD += nowD;
                curr = next; 
            }
            totalD +=  Math.abs(E[0]-curr.r) + Math.abs(E[1]-curr.c);

            answer = Math.min(answer,totalD);
            return;
        }

        for(int i=start; i<10; i++){
            if(location[i] == null) continue;
            comb[idx]= i;
            choose(i+1,idx+1);
        }
    }
}
