class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // value -> its next greater value
        HashMap<Integer, Integer> map = new HashMap<>();
        
        Stack<Integer> st = new Stack<>();
        
        // Build next greater map for all elements in nums2
        for (int num : nums2) {
            while (!st.isEmpty() && st.peek() < num) {
                map.put(st.pop(), num);
            }
            st.push(num);
        }
        
        // Elements left in stack have no greater element to their right
        // map doesn't contain them → we'll return -1 for them
        
        int[] result = new int[nums1.length];
        
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }
}