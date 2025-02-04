import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] memo = new int[46];
        memo[1]=  1;
        memo[2] = 1;
        for(int i=3; i<=N; i++){
            memo[i] = memo[i-1] + memo[i-2];
        }

        System.out.print(memo[N]);
    }
}