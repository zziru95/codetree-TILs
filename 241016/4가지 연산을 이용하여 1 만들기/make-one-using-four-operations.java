import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[2*N+1];
        visited[N] = true;
        System.out.print(bfs());

    }

    public static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{N,0});
        while(!q.isEmpty()){
            int[] current = q.poll();
            int now = current[0];
            int t = current[1];
            if(now==1) return t;
            for(int temp : search(now)){
                if(!visited[temp]){
                    q.add(new int[]{temp,t+1});
                    visited[temp]= true;;
                }
            }
        }


        return -1;
    }

    public static ArrayList<Integer> search(int n){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(n-1);
        temp.add(n+1);
        if(n!=0 &&n%2 ==0){
            temp.add(n/2);
        }
        if(n!=0 &&n%3 ==0){
            temp.add(n/3);
        }
        return temp;
    }
}