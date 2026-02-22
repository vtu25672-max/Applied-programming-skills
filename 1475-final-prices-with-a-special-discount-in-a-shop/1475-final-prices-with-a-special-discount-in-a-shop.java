class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = prices.clone();  // or new int[n] and copy
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // Find the first j > i with prices[j] <= prices[i]
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int prev = stack.pop();
                ans[prev] -= prices[i];   // apply discount
            }
            stack.push(i);
        }
        
        return ans;
    }
}