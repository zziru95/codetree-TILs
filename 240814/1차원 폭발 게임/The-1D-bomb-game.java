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
        boolean check2 = true;  // 폭발이 일어나는지 여부를 나타내는 변수

        while (check2) {
            int cnt = 1;
            boolean check = false;
            check2 = false;
            int s = -1;

            for (int i = 1; i < bombs.length; i++) {
                if (bombs[i] == bombs[i - 1]) {
                    cnt++;
                    if (s == -1) s = i - 1;
                    if (cnt >= M) {
                        check = true;
                        check2 = true;
                    }
                } else {
                    cnt = 1;
                    if (check) {
                        for (int j = s; 0 <= j && j <= i - 1; j++) {
                            bombs[j] = 0;
                        }
                    }
                    s = -1;
                    check = false;
                }
            }

            if (check) {
                for (int i=s; i<bombs.length; i++) {
                    bombs[i] = 0;
                }
            }


            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < bombs.length; i++) {
                if (bombs[i] != 0) arr.add(bombs[i]);
            }

            if (arr.size() > 0) {
                bombs = arr.stream().mapToInt(i -> i).toArray();
            } else {
                answerCheck = true;
                break;
            }
        }
    }




    public static void printAnswer() {
        System.out.println(bombs.length);
        for(int i=0; i<bombs.length; i++) {
            if(bombs[i] !=0 ) System.out.println(bombs[i]);
        }
    }
}