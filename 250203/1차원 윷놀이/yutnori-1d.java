import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static int[] dice;
    static int score = 0;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dice = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }
        roll(0,0,new int[m]);
        System.out.print(score);
    }


    public static void roll(int ns, int idx, int[] board){
        score = Math.max(ns,score);
        if(idx== n) return;

        for(int i=0; i<k; i++){
            if(board[i] == m ) continue;
            int[] newBoard = board.clone();
            int nextScore = ns;
            newBoard[i] += dice[idx];
            if(newBoard[i]>= m-1){
                newBoard[i] = m;
                nextScore++;
            }

            roll(nextScore, idx+1, newBoard);
        }
    }
}

