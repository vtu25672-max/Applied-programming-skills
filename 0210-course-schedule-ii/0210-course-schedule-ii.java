import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Build the adjacency list and in-degree array
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisiteCourse = prereq[1];
            adj.get(prerequisiteCourse).add(course);
            inDegree[course]++;
        }

        // Initialize a queue with courses that have no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Perform topological sort
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            order.add(currentCourse);

            // For each neighbor of the current course, decrement its in-degree
            for (int neighbor : adj.get(currentCourse)) {
                inDegree[neighbor]--;
                // If a neighbor's in-degree becomes 0, add it to the queue
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If the number of courses in the order is not equal to the total number of courses,
        // it means there's a cycle, and it's impossible to finish all courses.
        if (order.size() != numCourses) {
            return new int[0];
        }

        // Convert the list to an array
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = order.get(i);
        }
        return result;
    }
}
