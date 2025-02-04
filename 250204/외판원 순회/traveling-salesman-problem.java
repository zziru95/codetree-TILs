import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int INF = Integer.MAX_VALUE;
    static int[][] distance, memo;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        distance = new int[n][n];
        memo = new int[n][(1<<n)];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(memo[i],-1);
            for(int j=0; j<n; j++){
                distance[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(tsp(0,1));


    }


    public static int tsp(int current, int visited){
        if(visited == (1<<n)-1){
            return distance[current][0] > 0 ? distance[current][0] : INF;
        }

        if(memo[current][visited] != -1){
            return memo[current][visited];
        }

        int minCost =INF;
        for(int next = 0; next < n ; next++){
            if((visited & (1<<next)) == 0 && distance[current][next] != 0){
                int newVisited = visited | (1<<next);
                int cost = distance[current][next] + tsp(next,newVisited);
                minCost = Math.min(minCost,cost);
            }
        }

        return memo[current][visited] = minCost;
    }
}