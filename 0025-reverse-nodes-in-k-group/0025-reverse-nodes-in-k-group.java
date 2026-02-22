/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        
        // Dummy node to handle head changes easily
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prevGroupEnd = dummy;
        
        while (true) {
            // Check if we have at least k nodes left
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k; i++) {
                kth = kth.next;
                if (kth == null) {
                    return dummy.next;   // not enough nodes → done
                }
            }
            
            // Now kth is the last node of current group
            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;
            
            // Reverse the group: groupStart → ... → kth
            reverse(groupStart, kth);
            
            // Connect previous group to new head (which is now kth)
            prevGroupEnd.next = kth;
            
            // Connect new tail (original groupStart) to next group
            groupStart.next = nextGroupStart;
            
            // Move prevGroupEnd to the end of this reversed group
            prevGroupEnd = groupStart;
        }
    }
    
    // Reverses nodes from start to end (inclusive)
    private void reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode curr = start;
        
        // We stop when we have reversed up to and including end
        while (prev != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }
}