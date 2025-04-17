/*
 * TC: O((h * w)^(hw P n))
 * SC: O(h * w)
 */
import java.util.LinkedList;
import java.util.Queue;


public class OptimalBuildingPlacement {

    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int w = 6, h = 7, n = 7;
        minDistance(w, h, n);
        System.out.println("Minimum distance: " + minDistance); // Output: 2
    }

    public static void minDistance(int w, int h, int n) {
        if (n >= w * h) {
            minDistance = 0;
            return; // all cells are buildings
        }

        // Create a grid of size h x w
        // Initialize all cells to -1 (indicating empty)
        int[][] grid = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grid[i][j] = -1;
            }
        }

        backtrack(grid, n, 0, 0);
    }

    /**
     * Backtrack to find the optimal building placement
     */
    static void backtrack(int[][] grid, int n, int x, int y) {
        // If we have placed all buildings, calculate the distance
        if(n == 0) {
            bfs(grid);
            return;
        }

        int h = grid.length;
        int w = grid[0].length;
        for(int i = x; i < h; i++) {
            for(int j = y; j < w; j++) {
                if(grid[i][j] == -1) {
                    // Place a building at (i, j)
                    grid[i][j] = 0;
                    // Recurse to place the next building
                    int nextX = (j == w - 1) ? i + 1 : i;
                    int nextY = (j == w - 1) ? 0 : j + 1;
                    backtrack(grid, n - 1, nextX, nextY);
                    // Backtrack: remove the building
                    grid[i][j] = -1;
                }
            }
        }
    }

    /**
     * BFS to calculate the distance from the buildings to the empty lots
     */
    static void bfs(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;
        boolean[][] visited = new boolean[h][w];

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(grid[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int distance = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for(int[] dir : directions) {
                    int newX = cell[0] + dir[0];
                    int newY = cell[1] + dir[1];
                    if(newX >= 0 && newX < h && newY >= 0 && newY < w && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            distance++;
        }
        // The distance is the number of layers we have traversed from the buildings
        minDistance = Math.min(minDistance, distance - 1);
    }

}
