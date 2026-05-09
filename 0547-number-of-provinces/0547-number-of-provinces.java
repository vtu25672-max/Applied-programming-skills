import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                bfs(isConnected, visited, i);
                // Alternatively, you can use DFS:
                // dfs(isConnected, visited, i);
            }
        }
        return provinces;
    }

    // Breadth-First Search (BFS) to mark all connected cities as visited
    private void bfs(int[][] isConnected, boolean[] visited, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
                if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Depth-First Search (DFS) to mark all connected cities as visited
    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println("Example 1 Output: " + sol.findCircleNum(isConnected1)); // Expected: 2

        // Example 2
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println("Example 2 Output: " + sol.findCircleNum(isConnected2)); // Expected: 3

        // Additional Test Case
        int[][] isConnected3 = {{1, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}};
        System.out.println("Test Case 3 Output: " + sol.findCircleNum(isConnected3)); // Expected: 2
    }
}
