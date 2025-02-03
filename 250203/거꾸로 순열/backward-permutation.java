import java.util.*;

public class Main {
    static int n;
    static int[] per;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        per = new int[n];
        visited = new boolean[n];

        choose(0);
        System.out.print(sb);
    }


    public static void choose(int idx){
        if(idx == n){
            for(int a : per){
                sb.append(a).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int a =n-1; a>=0; a--){
            if(visited[a]) continue;

            visited[a] = true;
            per[idx] = a+1;
            choose(idx+1);
            visited[a] = false;
        }
    }
}