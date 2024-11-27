import java.util.*;

public class Main {
    static ArrayList<Integer> v = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void push_back(int data){
        v.add(data);
    }

    public static void pop_back(){
        if (!v.isEmpty()) {
            v.remove(v.size() - 1); 
        }
    }

    public static void get(int k){
        if (k > 0 && k <= v.size()) {
            System.out.println(v.get(k - 1));
        }
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        sc.nextLine();
    
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            String command = parts[0];
            switch (command) {
                case "push_back":
                    int value = Integer.parseInt(parts[1]);
                    push_back(value);
                    break;
                case "pop_back":
                    pop_back();
                    break;
                case "size":
                    System.out.println(v.size());
                    break;
                case "get":
                    int k = Integer.parseInt(parts[1]);
                    get(k);
                    break;
                default:
                    break;
            }
        }
    }
}
