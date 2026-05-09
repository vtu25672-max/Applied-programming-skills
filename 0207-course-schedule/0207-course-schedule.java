import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build the adjacency list representation of the graph
        // graph[i] will store a list of courses that depend on course i
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Calculate the in-degree for each course
        // inDegree[i] will store the number of prerequisites for course i
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int courseToTake = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph.get(prerequisiteCourse).add(courseToTake);
            inDegree[courseToTake]++;
        }

        // Initialize a queue with courses that have no prerequisites (in-degree 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Perform topological sort (Kahn's algorithm)
        int coursesTaken = 0;
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            coursesTaken++;

            // For each course that depends on the current course
            for (int neighbor : graph.get(currentCourse)) {
                inDegree[neighbor]--; // Decrement the in-degree of the dependent course
                // If the dependent course now has no prerequisites, add it to the queue
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If the number of courses taken equals the total number of courses,
        // it means all courses can be finished (no cycles).
        return coursesTaken == numCourses;
    }
}
