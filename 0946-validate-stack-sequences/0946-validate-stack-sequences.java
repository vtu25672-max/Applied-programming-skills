class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int j = 0;  // index in popped array
        
        for (int val : pushed) {
            st.push(val);
            
            // Pop as much as possible while top matches next expected
            while (!st.isEmpty() && st.peek() == popped[j]) {
                st.pop();
                j++;
            }
        }
        
        return j == popped.length;
    }
}