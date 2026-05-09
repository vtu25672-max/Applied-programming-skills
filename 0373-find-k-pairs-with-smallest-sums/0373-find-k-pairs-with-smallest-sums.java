class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        
        // Edge cases
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        
        int n = nums1.length;
        
        // Min-heap: [nums1[i], nums2[j], j]
        // Ordered by nums1[i] + nums2[j]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> (a[0] + a[1]) - (b[0] + b[1])
        );
        
        // Initialize: add (nums1[i], nums2[0]) for all i
        for (int i = 0; i < Math.min(n, k); i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        
        // Extract k smallest pairs
        while (k-- > 0 && !pq.isEmpty()) {
            int[] curr = pq.poll();
            res.add(Arrays.asList(curr[0], curr[1]));
            
            // Add next pair from same nums1 element
            int nextJ = curr[2] + 1;
            if (nextJ < nums2.length) {
                pq.offer(new int[]{curr[0], nums2[nextJ], nextJ});
            }
        }
        
        return res;
    }
}