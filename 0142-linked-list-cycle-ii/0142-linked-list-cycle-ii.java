public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        // Step 1: Find meeting point inside cycle (if exists)
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Step 2: Reset one pointer to head
                slow = head;
                
                // Step 3: Move both one step at a time
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                
                return slow;  // cycle entrance
            }
        }
        
        return null;  // no cycle
    }
}