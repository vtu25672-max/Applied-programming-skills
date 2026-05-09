import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // Assign a unique group ID to items that don't belong to any group.
        // This ensures each item has a group, even if it's a temporary one.
        int currentGroup = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = currentGroup++;
            }
        }

        // Build adjacency lists and in-degree arrays for both item-level and group-level dependencies.
        List<List<Integer>> itemGraph = new ArrayList<>();
        int[] itemInDegree = new int[n];
        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] groupInDegree = new int[currentGroup]; // currentGroup is the total number of groups including temporary ones

        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < currentGroup; i++) {
            groupGraph.add(new ArrayList<>());
        }

        // Populate the graphs and in-degree arrays based on beforeItems.
        for (int i = 0; i < n; i++) {
            for (int prevItem : beforeItems.get(i)) {
                itemGraph.get(prevItem).add(i); // Add edge from prevItem to i
                itemInDegree[i]++; // Increment in-degree of i

                // If prevItem and i belong to different groups, add a group dependency.
                if (group[prevItem] != group[i]) {
                    groupGraph.get(group[prevItem]).add(group[i]); // Add edge from group[prevItem] to group[i]
                    groupInDegree[group[i]]++; // Increment in-degree of group[i]
                }
            }
        }

        // Perform topological sort on items.
        List<Integer> sortedItems = topologicalSort(itemGraph, itemInDegree, n);
        if (sortedItems.size() != n) {
            return new int[0]; // Cycle detected in item dependencies.
        }

        // Perform topological sort on groups.
        List<Integer> sortedGroups = topologicalSort(groupGraph, groupInDegree, currentGroup);
        if (sortedGroups.size() != currentGroup) {
            return new int[0]; // Cycle detected in group dependencies.
        }

        // Organize items by their groups based on the sorted items.
        Map<Integer, List<Integer>> itemsInGroup = new HashMap<>();
        for (int item : sortedItems) {
            itemsInGroup.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // Construct the final sorted array by iterating through sorted groups
        // and appending the sorted items within each group.
        int[] result = new int[n];
        int index = 0;
        for (int groupId : sortedGroups) {
            if (itemsInGroup.containsKey(groupId)) {
                for (int item : itemsInGroup.get(groupId)) {
                    result[index++] = item;
                }
            }
        }

        return result;
    }

    // Helper function for topological sort using Kahn's algorithm (BFS).
    private List<Integer> topologicalSort(List<List<Integer>> graph, int[] inDegree, int size) {
        List<Integer> sortedOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Add all nodes with an in-degree of 0 to the queue.
        for (int i = 0; i < size; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            sortedOrder.add(u);

            // For each neighbor of the current node, decrement its in-degree.
            // If a neighbor's in-degree becomes 0, add it to the queue.
            for (int v : graph.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // If the sorted order contains all nodes, it's a valid topological sort.
        // Otherwise, a cycle exists.
        return sortedOrder.size() == size ? sortedOrder : new ArrayList<>();
    }
}
