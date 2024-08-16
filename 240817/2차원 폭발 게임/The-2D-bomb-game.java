import java.util.*;
import java.io.*;

public class Main {
    static int N,M,K;
    static int[][] boxes;
    static boolean check = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boxes = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                boxes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            bomb();
            turn();
            gravity();
        }

        check = true;

        while (check) {
            bomb();
        }

        printAnswer();


    }

    public static void bomb() {
        check = false;

        for (int j=0;j<N;j++) {
            int cnt = 1;
            int start = -1;
            for(int i=1;i<N;i++) {
                if (boxes[i-1][j] == boxes[i][j] && boxes[i][j] != 0) {
                    cnt++;
                    if (start == -1) {
                        start = i-1;
                    }
                } else {
                    if(cnt >= M && start != -1) {
                        int end = i-1;
                        for (int m=start; m<=end; m++) {
                            boxes[m][j] = 0;
                        }
                        check = true;
                    }

                    cnt = 1;
                    start = -1;
                }
            }
            if (start != -1 && cnt>=M) {
                for (int m=start; m<N; m++) {
                    boxes[m][j] =0;
                }
                check = true;
            }
        }
        gravity();
        if (check) {
            bomb();
        }
    }

    public static void turn() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[j][N - 1 - i] = boxes[i][j];
            }
        }

        boxes = temp;
    }

    public static void gravity() {
        int[][] temp = new int[N][N];
        for (int j = 0; j < N; j++) {
            int idx = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (boxes[i][j] !=0) {
                    temp[idx][j] = boxes[i][j];
                    idx--;
                }
            }
        }
        boxes = temp;
    }


    public static void printAnswer() {
        if (M==1) {
            System.out.println(0);
            return;
        }
        int cnt =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (boxes[i][j] !=0) cnt++;
            }

        }

        System.out.println(cnt);
    }


    public static void printDebug() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(boxes[i][j]);
                if (j<N-1) sb.append(" ");
            }
            if (i<N-1) sb.append(System.lineSeparator());
        }

        System.out.println(sb);
        System.out.println("____________");
    }
}