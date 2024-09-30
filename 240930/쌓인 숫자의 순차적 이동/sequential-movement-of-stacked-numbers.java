import java.util.*;
import java.io.*;


public class Main {
    static int n,m;
    static Deque<Integer>[][] arr;
    static int[][] direction = { {1,0}, {-1,0}, {0,1}, {0,-1}, {1,-1}, {1,1}, {-1,-1}, {-1,1} };


    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new Deque[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = new LinkedList<>();
                arr[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            move(Integer.parseInt(st.nextToken()));
        }
        printAnswer();
    }



    public static void move(int num) {
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j].contains(num)) {
                    int maxValue = -1;
                    int nextR = -1;
                    int nextC = -1;
                    Stack<Integer> stack = new Stack<>();
                    for(int d=0; d<8; d++){
                        int nr = i+ direction[d][0];
                        int nc = j+ direction[d][1];
                        if( 0<= nr && nr< n && 0<= nc&& nc< n){
                            for(int temp: arr[nr][nc]){
                                if(temp>maxValue){
                                    maxValue = temp;
                                    nextR = nr;
                                    nextC = nc;
                                }
                            }
                        }
                    }

                    while(!arr[i][j].isEmpty()){
                        int temp = arr[i][j].poll();
                        stack.add(temp);
                         if(temp == num){
                             break;
                         }

                    }
                    while(!stack.isEmpty()){
                        arr[nextR][nextC].addFirst(stack.pop());
                    }
                    return;
                }
            }
        }
        // printAnswer();
    }

    public static void printAnswer(){
        StringBuilder sb =new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(arr[i][j].isEmpty()) {
                    sb.append("None");
                } else {
                    while(!arr[i][j].isEmpty()){
                        sb.append(arr[i][j].poll());
                        if(!arr[i][j].isEmpty()) sb.append(" ");
                    }
                }
                if(i<n-1 || j <n-1) sb.append(System.lineSeparator());
            }
        }

        System.out.print(sb);
    }
}