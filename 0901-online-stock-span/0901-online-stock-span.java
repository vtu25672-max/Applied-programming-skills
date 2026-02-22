class StockSpanner {
    
    // Stores [price, span] pairs
    // Maintains decreasing order of prices (top has smallest price)
    private Stack<int[]> stack;
    
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int days = 1;  // include today
        
        // Merge spans of all previous smaller-or-equal prices
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            days += stack.pop()[1];
        }
        
        // Push current day with its computed span
        stack.push(new int[]{price, days});
        
        return days;
    }
}