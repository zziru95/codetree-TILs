import java.util.*;
public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();
    static int[] perm;
    static boolean[] visited;
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        perm = new int[n];
        visited = new boolean[n];
        choose(0);

        System.out.print(sb);
        
    }


    public static void choose(int idx){
        if(idx==n){
            for(int a : perm){
                sb.append(a).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
            return;
        }
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                perm[idx] = i+1;
                choose(idx+1);
                visited[i] = false;
            }
        }
    }
}