import java.util.*;

class RecentCounter {

    private Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        // Add current request
        queue.offer(t);

        // Remove outdated requests
        while (!queue.isEmpty() && queue.peek() < t - 3000) {
            queue.poll();
        }

        // Return number of valid requests
        return queue.size();
    }
}