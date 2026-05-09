class Solution {
    /**
     * Finds the maximum area of an island in a given binary matrix.
     * An island is a group of 1s connected 4-directionally.
     *
     * @param grid The m x n binary matrix representing the grid.
     * @return The maximum area of an island, or 0 if no islands exist.
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;

        // Iterate through each cell of the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a land cell (1) is found, start a DFS to find the area of the island
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }

        return maxArea;
    }

    /**
     * Performs Depth First Search (DFS) to calculate the area of an island.
     * Marks visited land cells as 0 to avoid recounting.
     *
     * @param grid The m x n binary matrix.
     * @param r    The current row index.
     * @param c    The current column index.
     * @return The area of the island connected to the starting cell (r, c).
     */
    private int dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        // Base cases for DFS:
        // 1. Out of bounds
        // 2. Cell is water (0)
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }

        // Mark the current cell as visited by changing it to 0
        grid[r][c] = 0;

        // Initialize the area for the current cell
        int area = 1;

        // Explore the 4-directional neighbors
        area += dfs(grid, r + 1, c); // Down
        area += dfs(grid, r - 1, c); // Up
        area += dfs(grid, r, c + 1); // Right
        area += dfs(grid, r, c - 1); // Left

        return area;
    }
}
