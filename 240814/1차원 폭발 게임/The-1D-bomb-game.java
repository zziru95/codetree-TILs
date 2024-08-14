import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] bombs;
    static boolean answerCheck = false;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bombs = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            bombs[i] = Integer.parseInt(st.nextToken());
        }

        if(M==1) {
            System.out.print(0);
        } else {
            bomb();
            if(answerCheck) System.out.print(0);
            else {
                printAnswer();
            }
        }

    }

    public static void bomb() {
        int cnt = 1;
        boolean check = false;
        boolean check2 = false;
        for(int i=1; i<bombs.length; i++) {
            if(bombs[i] == bombs[i-1]) {
                cnt++;
                if(cnt>=M) check = true;
                if(check) {
                    check2 = true;
                    bombs[i] = 0;
                    bombs[i-1] = 0;
                }
            } else {
                cnt = 1;
                check = false;
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for( int i=0; i<bombs.length; i++) {
            if (bombs[i] != 0) arr.add(bombs[i]);
        }

        if(arr.size()>0) {
            bombs = arr.stream().mapToInt(i -> i).toArray();
            if(check2) bomb();
        } else {
            answerCheck = true;
        }
        
    }

    public static void printAnswer() {
        System.out.println(bombs.length);
        for(int i=0; i<bombs.length; i++) {
            if(bombs[i] !=0 ) System.out.println(bombs[i]);
        }
    }
}