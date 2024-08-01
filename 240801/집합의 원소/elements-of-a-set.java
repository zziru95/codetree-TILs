import.java.util.*;
import.java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] uf = new int[n+1];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(command == 0) {
                union(uf,u,v);
             } else {
                if(find(uf,u) == find(uf,v)) {
                    System.out.println(1);
                } else {
                   System.out.println(0); 
                }
             }

        }
    }

    public static int find(int[] uf, int X) {
        if(uf[x]==x) return x;

        rootNode = find(uf[x]);
        uf[x] = rootNode;
        return rootNode;
    }

    public static void union(int[] uf, int x, int y) {
        int X = find(uf,x);
        int Y = find(uf,y);
        if(X==Y) return;
        uf[Math.max(X,Y)] = Math.min(X,Y);
    }
}