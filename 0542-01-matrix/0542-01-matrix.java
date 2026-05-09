import java.util.LinkedList;
import java.util.Queue;

class Solution {
    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     * The distance between two cells sharing a common edge is 1.
     *
     * @param mat The input m x n binary matrix.
     * @return The distance of the nearest 0 for each cell.
     */
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[0][0];
        }

        int m = mat.length;
        int n = mat[0].length;
        int[][] dist = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        // Initialize the distance matrix and the queue
        // For cells with 0, the distance is 0, and they are the starting points for BFS.
        // For cells with 1, initialize their distance to infinity (or a large number)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Directions for moving to adjacent cells (up, down, left, right)
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Perform Breadth-First Search (BFS)
        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int r = currentCell[0];
            int c = currentCell[1];

            // Explore neighbors
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check if the neighbor is within bounds
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    // If the current distance to the neighbor is greater than the distance
                    // through the current cell + 1, update the neighbor's distance
                    // and add it to the queue for further exploration.
                    if (dist[nr][nc] > dist[r][c] + 1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return dist;
    }
}
