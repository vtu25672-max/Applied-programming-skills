class Solution {
    /**
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
     * return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * @param grid The 2D binary grid representing the map.
     * @return The number of islands.
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        int islandCount = 0;

        // Iterate through each cell in the grid
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                // If we find a land cell ('1') that hasn't been visited yet
                if (grid[r][c] == '1') {
                    // Increment the island count and start a Depth First Search (DFS)
                    // to mark all connected land cells as visited.
                    islandCount++;
                    dfs(grid, r, c);
                }
            }
        }

        return islandCount;
    }

    /**
     * Performs Depth First Search (DFS) to mark all connected land cells as visited.
     *
     * @param grid The 2D binary grid.
     * @param r    The current row index.
     * @param c    The current column index.
     */
    private void dfs(char[][] grid, int r, int c) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Base cases for DFS:
        // 1. If the current cell is out of bounds.
        // 2. If the current cell is water ('0').
        // 3. If the current cell has already been visited (marked as '0').
        if (r < 0 || r >= numRows || c < 0 || c >= numCols || grid[r][c] == '0') {
            return;
        }

        // Mark the current cell as visited by changing it to '0' (water).
        grid[r][c] = '0';

        // Recursively call DFS for all adjacent cells (up, down, left, right).
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}
