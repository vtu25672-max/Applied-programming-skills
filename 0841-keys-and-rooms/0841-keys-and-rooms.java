import java.util.*;

class Solution {
    /**
     * Determines if all rooms can be visited given the keys available in each room.
     *
     * @param rooms A 2D array where rooms[i] is a list of keys found in room i.
     * @return true if all rooms can be visited, false otherwise.
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> keys = new LinkedList<>();

        // Start with room 0, which is unlocked
        visited[0] = true;
        keys.add(0); // Add the key to room 0 to the queue

        // Process keys from the queue
        while (!keys.isEmpty()) {
            int currentRoomKey = keys.poll();

            // Collect keys from the current room
            for (int nextRoomKey : rooms.get(currentRoomKey)) {
                // If the room hasn't been visited yet, mark it as visited and add its key to the queue
                if (!visited[nextRoomKey]) {
                    visited[nextRoomKey] = true;
                    keys.add(nextRoomKey);
                }
            }
        }

        // Check if all rooms have been visited
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false; // If any room is not visited, return false
            }
        }

        return true; // All rooms have been visited
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(Arrays.asList(1));
        rooms1.add(Arrays.asList(2));
        rooms1.add(Arrays.asList(3));
        rooms1.add(new ArrayList<>());
        System.out.println("Example 1 Output: " + sol.canVisitAllRooms(rooms1)); // Expected: true

        // Example 2
        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));
        System.out.println("Example 2 Output: " + sol.canVisitAllRooms(rooms2)); // Expected: false
    }
}
