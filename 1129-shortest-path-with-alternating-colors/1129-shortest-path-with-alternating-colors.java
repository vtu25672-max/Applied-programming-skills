import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // Create adjacency lists for red and blue edges.
        // adj[color][node] will store a list of nodes reachable from 'node' via an edge of 'color'.
        // color 0 for red, color 1 for blue.
        List<Integer>[][] adj = new List[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                adj[i][j] = new ArrayList<>();
            }
        }

        // Populate adjacency lists
        for (int[] edge : redEdges) {
            adj[0][edge[0]].add(edge[1]); // Red edge from edge[0] to edge[1]
        }
        for (int[] edge : blueEdges) {
            adj[1][edge[0]].add(edge[1]); // Blue edge from edge[0] to edge[1]
        }

        // Initialize the result array with -1, meaning no path found yet.
        // The distance to node 0 from itself is 0.
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        answer[0] = 0;

        // We need to keep track of visited states to avoid cycles and redundant computations.
        // visited[node][color] will be true if we have visited 'node' ending with an edge of 'color'.
        boolean[][] visited = new boolean[n][2];

        // Use a queue for BFS. Each element in the queue will be an array: [node, color, distance].
        // 'color' here represents the color of the edge *used to reach* the current 'node'.
        // 0 for red, 1 for blue.
        Queue<int[]> queue = new LinkedList<>();

        // Start BFS from node 0.
        // We can reach node 0 with a path of length 0.
        // We can consider starting with either a red or a blue edge from node 0 in the next step.
        // So, we add two initial states to the queue:
        // [node, color_of_edge_taken_to_reach_this_node, distance]
        // For node 0, the distance is 0. We don't have a "previous edge color" in the standard sense.
        // We can think of it as being "ready" to take either a red or blue edge.
        // To handle this, we can push states representing "reached node 0, next edge can be red" and
        // "reached node 0, next edge can be blue".
        // A cleaner way is to initialize the queue with neighbors of node 0 directly, or,
        // as done below, by considering the "virtual" previous edge color that allows the first step.
        // Let's add starting points for BFS that represent arriving at node 0.
        // The key is that the *next* edge we take must be of the *opposite* color.
        // We can initialize the queue with two "dummy" states:
        // 1. Reached node 0, the *previous* edge was "not red" (so the next can be red). Distance 0.
        // 2. Reached node 0, the *previous* edge was "not blue" (so the next can be blue). Distance 0.
        // However, a more standard BFS approach is to add the direct neighbors.

        // Let's refine: The state should be `[current_node, color_of_edge_that_LED_to_this_node, distance]`
        // For the starting node 0, distance is 0.
        // The "color of edge that led to this node" is tricky. It's more about what color edge we *expect* to take next.
        // A common BFS formulation for this problem is to track `(node, color_of_last_edge_taken, distance)`.
        // If `color_of_last_edge_taken` is -1 (or some indicator), it means we are at the start or can take any color.

        // Let's try a BFS where the queue stores `[current_node, color_of_edge_taken_to_reach_current_node, distance]`
        // For node 0, the distance is 0. The "color of edge taken" is undefined.
        // We can use a sentinel value, say -1, to indicate the start.
        // The problem is that when we are at node 0, we can take *either* a red or blue edge.
        // This suggests we need to track distances based on the *last edge color*.

        // Let's use `dist[node][color]` to store the shortest distance to `node` ending with an edge of `color`.
        int[][] dist = new int[n][2]; // dist[node][0] = shortest path to node ending with red edge
                                      // dist[node][1] = shortest path to node ending with blue edge
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Initialize distances for node 0.
        dist[0][0] = 0; // Shortest path to node 0 ending with a conceptual "red" edge (for starting purposes)
        dist[0][1] = 0; // Shortest path to node 0 ending with a conceptual "blue" edge (for starting purposes)

        // Queue stores [node, color_of_edge_taken_to_reach_this_node]
        // We will infer distance from the `dist` array.
        Queue<int[]> bfsQueue = new LinkedList<>();
        bfsQueue.offer(new int[]{0, 0}); // Start at node 0, conceptual previous edge was red
        bfsQueue.offer(new int[]{0, 1}); // Start at node 0, conceptual previous edge was blue

        while (!bfsQueue.isEmpty()) {
            int[] current = bfsQueue.poll();
            int u = current[0];
            int lastColor = current[1]; // 0 for red, 1 for blue. This is the color of the edge that led to 'u'.

            // The next edge we look for must be of the opposite color.
            int nextColor = 1 - lastColor;

            // Iterate through neighbors reachable by an edge of `nextColor` from node `u`.
            for (int v : adj[nextColor][u]) {
                // If we found a shorter path to `v` ending with an edge of `nextColor`
                if (dist[u][lastColor] + 1 < dist[v][nextColor]) {
                    dist[v][nextColor] = dist[u][lastColor] + 1;
                    // Add the new state to the queue. The path to 'v' ends with an edge of 'nextColor'.
                    bfsQueue.offer(new int[]{v, nextColor});
                }
            }
        }

        // Now, construct the final answer array.
        // For each node `x`, the shortest alternating path is the minimum of the shortest path ending with a red edge
        // and the shortest path ending with a blue edge.
        for (int i = 0; i < n; i++) {
            int minPath = Math.min(dist[i][0], dist[i][1]);
            if (minPath == Integer.MAX_VALUE) {
                answer[i] = -1; // No alternating path found
            } else {
                answer[i] = minPath;
            }
        }

        return answer;
    }
}
