import java.util.*;
import java.io.*;

public class Main {
    static boolean check = true;
    static int[] blocks;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        blocks = new int[n];


        for(int i=0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<2; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            for(int j=s; j<=e; j++) {
                blocks[j] = 0;
            }
            replace(blocks.length, blocks.length-(e-s+1));
        }

        if(check) {
            System.out.println(blocks.length);
            for(int i=0; i<blocks.length; i++) {
                if (blocks[i] !=0) System.out.println(blocks[i]);
            }
        } else {
            System.out.println(0);
        }







    }

    public static void replace(int n, int m){
        if (m==0) {
            check = false;
            return;
        }
        int[] temp = new int[m];
        int idx = 0;
        for(int i=0; i<n; i++) {
            if (blocks[i] ==0) continue;
            temp[idx] = blocks[i];
            idx++;
        }
        blocks = temp;
    }
}