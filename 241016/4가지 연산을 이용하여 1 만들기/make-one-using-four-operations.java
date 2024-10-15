import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        // System.out.print(bfs());
        int[] memo = new int[N+1];
        
        for(int i=2;i<=N;i++){
            memo[i] = memo[i-1] +1;
            if(i%2==0){
                memo[i] = Math.min(memo[i], memo[i/2]+1);
            }
            if(i%3==0){
                memo[i] = Math.min(memo[i], memo[i/3]+1);
            }
        }

        System.out.print(memo[N]);

    }

    // public static int bfs(){
    //     Queue<int[]> q = new ArrayDeque<>();
    //     Set<Integer> visited = new HashSet<>();
    //     q.add(new int[]{N,0});
    //     visited.add(N);
    //     while(!q.isEmpty()){
    //         int[] current = q.poll();
    //         int now = current[0];
    //         int t = current[1];
    //         if(now==1) return t;
    //         for(int temp : search(now)){
    //             if(!visited.contains(temp)){
    //                 q.add(new int[]{temp,t+1});
    //                 visited.add(temp);
    //             }
    //         }
    //     }


    //     return -1;
    // }

    // public static ArrayList<Integer> search(int n){
    //     ArrayList<Integer> temp = new ArrayList<>();
    //     temp.add(n-1);
    //     temp.add(n+1);
    //     if(n!=0 &&n%2 ==0){
    //         temp.add(n/2);
    //     }
    //     if(n!=0 &&n%3 ==0){
    //         temp.add(n/3);
    //     }
    //     return temp;
    // }
}