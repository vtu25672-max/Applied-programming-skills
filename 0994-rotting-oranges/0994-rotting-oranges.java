import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        // Initialize the queue with all rotten oranges and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0}); // {row, col, minutes}
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // If there are no fresh oranges initially, return 0
        if (freshOranges == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // Right, Left, Down, Up

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int time = current[2];

            minutes = time; // Update the maximum minutes elapsed

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check if the neighbor is within bounds and is a fresh orange
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2; // Rot the fresh orange
                    freshOranges--;
                    queue.offer(new int[]{nr, nc, time + 1}); // Add to queue with incremented time
                }
            }
        }

        // If there are still fresh oranges left, it's impossible to rot them all
        if (freshOranges > 0) {
            return -1;
        }

        return minutes;
    }
}
