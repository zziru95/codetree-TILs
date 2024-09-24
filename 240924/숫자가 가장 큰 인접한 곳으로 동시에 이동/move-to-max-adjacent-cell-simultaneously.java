import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t;
    static int[][] grid;
    // Directions: up, down, left, right
    static int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Read grid size, initial particle count, and time steps
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // Read grid values
        grid = new int[n][n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Read initial particle positions
        List<int[]> particles = new ArrayList<>();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1; // 0-based index
            int c = Integer.parseInt(st.nextToken()) -1;
            particles.add(new int[]{r, c});
        }

        // Simulate for t time steps
        for(int time=0; time<t; time++) {
            // Map to count how many particles move to each cell
            int[][] temp = new int[n][n];
            List<int[]> newParticles = new ArrayList<>();

            // First, determine target cells for all particles
            for(int[] particle : particles) {
                int r = particle[0];
                int c = particle[1];
                int currentValue = grid[r][c];

                int targetR = r;
                int targetC = c;
                int maxValue = currentValue;

                // Find the target cell to move
                for(int d=0; d<4; d++) {
                    int nr = r + directions[d][0];
                    int nc = c + directions[d][1];
                    if(nr >=0 && nr <n && nc >=0 && nc <n) {
                        if(grid[nr][nc] > maxValue) {
                            maxValue = grid[nr][nc];
                            targetR = nr;
                            targetC = nc;
                        }
                    }
                }

                // If moving to higher cell, move
                if(maxValue > currentValue) {
                    temp[targetR][targetC]++;
                    // Store the target cell for later processing
                    newParticles.add(new int[]{targetR, targetC});
                }
                else {
                    temp[r][c]++;
                    // Store the current cell as target (no movement)
                    newParticles.add(new int[]{r, c});
                }
            }

            // Now, handle collisions
            List<int[]> survivingParticles = new ArrayList<>();
            for(int i=0; i<newParticles.size(); i++) {
                int[] pos = newParticles.get(i);
                int r = pos[0];
                int c = pos[1];
                if(temp[r][c] ==1) {
                    survivingParticles.add(new int[]{r, c});
                }
                // Else, collision occurred, particle is removed
            }

            // Update particles list
            particles = survivingParticles;

            // Early termination if no particles left
            if(particles.isEmpty()) {
                break;
            }
        }

        // Output the final count of particles
        System.out.print(particles.size());
    }
}