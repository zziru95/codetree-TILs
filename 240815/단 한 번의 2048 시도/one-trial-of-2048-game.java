import java.util.*;
import java.io.*;
public class Main {
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        graph = new int[4][4];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        play(st.nextToken());
        printAnswer();


    }


    public static void play(String dir) {
        int[][] temp = new int[4][4];
        int start = 3;
        boolean[][] visited = new boolean[4][4];

        if(dir.equals("L") || dir.equals("U")) start = 0;
        for(int i=0; i<4; i++) {
            int idx = start;
            for(int j=0; j<4; j++) {
                if(dir.equals("R")) {
                    if(graph[i][3-j] !=0) {
                        temp[i][idx] = graph[i][3-j];

                        if(idx != start && temp[i][idx] == temp[i][idx+1] && !visited[i][idx+1]) {
                            temp[i][idx] = 0;
                            temp[i][idx+1] *= 2;
                            visited[i][idx+1] = true;
                        } else{
                            idx--;
                        }
                    }

                } else if(dir.equals("L")) {
                    if(graph[i][j] !=0 ) {
                        temp[i][idx] = graph[i][j];

                        if(idx != start && temp[i][idx] == temp[i][idx-1]&& !visited[i][idx-1]) {
                            temp[i][idx] = 0;
                            temp[i][idx-1] *= 2;
                            visited[i][idx-1] = true;
                        } else{
                            idx++;
                        }
                    }
                } else if(dir.equals("D")) {
                    if(graph[3-j][i] !=0) {
                        temp[idx][i] = graph[3-j][i];
                        if(idx != start && temp[idx][i] == temp[idx+1][i] && !visited[idx+1][i]) {
                            temp[idx][i] = 0;
                            temp[idx+1][i] *= 2;
                            visited[idx+1][i] = true;
                        } else{
                            idx--;
                        }
                    }
                } else if(dir.equals("U")) {
                    if(graph[j][i] !=0) {
                        temp[idx][i] = graph[j][i];
                        if(idx != start && temp[idx][i] == temp[idx-1][i] && !visited[idx-1][i]) {
                            temp[idx][i] = 0;
                            temp[idx-1][i] *= 2;
                            visited[idx-1][i] = true;
                        } else{
                            idx++;
                        }
                    }
                }
            }
        }
        graph = temp;
    }

    public static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++) {

                sb.append(graph[i][j]);

                if(j<3) sb.append(" ");
            }
            if(i<3) sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}