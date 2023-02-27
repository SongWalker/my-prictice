package lc.my;

public class lc2 {

    public static void main(String[] args) {

    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0, null);
        ListNode head = l3;
        int delta = 0; //进位
        while (true) {
            int value = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + delta;
            if(value > 9) {
                value = value - 10;
                delta = 1;
            }
            else {
                delta = 0;
            }
            l3.val = value;

            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
            if(l1 != null || l2 != null || delta > 0) {
                l3.next = new ListNode(0, null);
                l3 = l3.next;
            }
            else {
                break;
            }
        }
        return head;
    }

}
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


