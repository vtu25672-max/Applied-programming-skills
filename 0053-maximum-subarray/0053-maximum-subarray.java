public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxSoFar = nums[0];      // best sum ending at current position
        int maxEndingHere = nums[0]; // overall maximum sum found so far
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend the previous subarray or start new from current element
            maxSoFar = Math.max(nums[i], maxSoFar + nums[i]);
            
            // Update global maximum
            maxEndingHere = Math.max(maxEndingHere, maxSoFar);
        }
        
        return maxEndingHere;
    }
}