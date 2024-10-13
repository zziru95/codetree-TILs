import java.util.*;
import java.io.*;

public class Main {
    static class Pair{
        int r,c;
        public Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static int[] comb = new int[3];
    static char[][] arr;
    static Pair[] pairs = new Pair[10];
    static ArrayList<Integer> coins = new ArrayList<>();
    static int minD = Integer.MAX_VALUE; 

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N][N];
        Pair start = null;
        Pair end = null; 

        for(int i=0; i<N;i++){
            String temp = br.readLine();
            for(int j=0; j<N;j++){
                arr[i][j] = temp.charAt(j);
                if(arr[i][j] == 'S') {
                    start = new Pair(i,j);
                } else if(arr[i][j]=='E'){
                    end = new Pair(i,j);
                } else if(Character.isDigit(arr[i][j])){
                    int temp2 = arr[i][j] - '0';
                    pairs[temp2] = new Pair(i,j);
                    coins.add(temp2);
                }
            }
        }

        Collections.sort(coins);
        choose(0,0,0,start,end);
        System.out.print(minD);
    }


    public static void choose(int now, int cnt, int distance, Pair start, Pair end){
        if(cnt==3){
            int last = comb[2];
            Pair lastP = pairs[last];
            int totalD = distance + Math.abs(end.r-lastP.r) + Math.abs(end.c-lastP.c);
            minD = Math.min(minD, totalD);
            return;
        }
        if(distance> minD){
            return;
        }

        for(int i=now; i<coins.size(); i++){
            int chooseNum = coins.get(i); 
            comb[cnt] = chooseNum;
            Pair next = pairs[chooseNum];
            Pair before =null;
            if(cnt==0){
                before = start;
            } else{
                before = pairs[comb[cnt-1]];
            }
            int newDistance = distance+Math.abs(start.r-next.r)+ Math.abs(start.c-next.c);
            choose(i+1,cnt+1,newDistance,start,end);
        }
    }
}