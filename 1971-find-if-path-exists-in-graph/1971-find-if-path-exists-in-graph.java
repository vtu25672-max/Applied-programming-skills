import java.util.*;

class Solution {
    /**
     * Checks if a valid path exists from a source vertex to a destination vertex in a bi-directional graph.
     *
     * @param n           The number of vertices in the graph.
     * @param edges       A 2D integer array representing the edges of the graph. Each edges[i] = [ui, vi] denotes an edge between vertex ui and vertex vi.
     * @param source      The starting vertex.
     * @param destination The target vertex.
     * @return true if a path exists from source to destination, false otherwise.
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Create an adjacency list to represent the graph.
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Populate the adjacency list from the given edges.
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]); // Since the graph is bi-directional
        }

        // Use Breadth-First Search (BFS) to find a path.
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // Start BFS from the source vertex.
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            // If we have reached the destination, a path exists.
            if (currentVertex == destination) {
                return true;
            }

            // Explore neighbors of the current vertex.
            for (int neighbor : adj.get(currentVertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // If the queue becomes empty and we haven't reached the destination, no path exists.
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 0}};
        int source1 = 0;
        int destination1 = 2;
        System.out.println("Example 1 Output: " + sol.validPath(n1, edges1, source1, destination1)); // Expected: true

        // Example 2
        int n2 = 6;
        int[][] edges2 = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        int source2 = 0;
        int destination2 = 5;
        System.out.println("Example 2 Output: " + sol.validPath(n2, edges2, source2, destination2)); // Expected: false

        // Additional Test Case
        int n3 = 5;
        int[][] edges3 = {{0, 1}, {1, 2}, {3, 4}};
        int source3 = 0;
        int destination3 = 4;
        System.out.println("Additional Test Case Output: " + sol.validPath(n3, edges3, source3, destination3)); // Expected: false

        int n4 = 5;
        int[][] edges4 = {{0, 1}, {1, 2}, {3, 4}, {2,3}};
        int source4 = 0;
        int destination4 = 4;
        System.out.println("Additional Test Case Output: " + sol.validPath(n4, edges4, source4, destination4)); // Expected: true
    }
}
