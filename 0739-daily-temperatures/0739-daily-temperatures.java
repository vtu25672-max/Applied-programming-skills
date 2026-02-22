class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        
        // Monotonic decreasing stack: stores indices
        // Stack maintains indices in decreasing order of temperatures
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // While stack is not empty and current temp is warmer than temp at stack top
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex;  // days to wait = current index - previous index
            }
            // Push current index (even if no pop happened)
            stack.push(i);
        }
        
        // Remaining indices in stack have no warmer day ahead → answer stays 0
        
        return answer;
    }
}