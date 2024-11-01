import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[] visited = new int[1001];
    static int[][] lines;
    static int maxCnt = 0;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringToken st = new StringToken(br.readLine());

        n = Integer.parseInt(st.nextToken());
        lines = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringToken(br.readLine());
            lines[i][0]=Integer.parseInt(st.nextToken());
            lines[i][1]=Integer.parseInt(st.nextToken());
        }

        choose(0,0);

        System.out.print(maxCnt);


    }




    public static void choose(int idx, int cnt){
        if(idx == n){
            maxCnt = Math.max(maxCnt,cnt);
            return;
        }

        ArrayList<Integer> temp = new ArrayList<>();
        int[] now  = lines[idx];
        boolean flag =true;
        
        int count = 0;
        for(int i=now[0]; i<=now[1]; i++){
            if(visited[i]) {
                flag= false;
                break;
            }
            temp.add(i);
            count++;
            visited[i] = true;
        }
        if(flag){
            choose(idx+1,cnt+count);
        }
        for(int back : temp){
            visited[i] = false;
        }
        choose(idx+1,cnt);
    }
}