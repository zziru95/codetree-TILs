import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] memo = new int[N+1];
        memo[0]=  0;
        memo[1] = 1;
        for(int i=2; i<=N; i++){
            memo[i] = memo[i-1] + memo[i-2];
        }

        System.out.print(memo[N]);
    }
}