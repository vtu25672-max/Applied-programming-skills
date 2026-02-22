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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode nextTemp = curr.next;    // save next node
            curr.next = prev;                 // reverse the link
            prev = curr;                      // move prev forward
            curr = nextTemp;                  // move curr forward
        }
        
        return prev;    // prev becomes the new head
    }
}