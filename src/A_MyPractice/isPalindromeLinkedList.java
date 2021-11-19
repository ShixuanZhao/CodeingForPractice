package A_MyPractice;


public class isPalindromeLinkedList {
    ListNode temp;

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        ListNode[] temp = new ListNode[] {head};
        return isPalindrome(head, temp);
    }

    private boolean isPalindrome(ListNode head, ListNode[] temp) {
        if (head == null) {
            return true;
        }
        boolean res = isPalindrome(head.next, temp) && head.val == temp[0].val;
        //因为如果直接temp = temp.next改了等于没改，因为是reference，等到return到上一层，就被GC了
        temp[0] = temp[0].next;
        //actually, temp is the start and the head is end
        return res;
    }

    public static void main(String[] args) {
        isPalindromeLinkedList test = new isPalindromeLinkedList();
        //1->2->2->1
        ListNode Node1 = new ListNode(1);
        ListNode Node2 = new ListNode(2);
        ListNode Node3 = new ListNode(2);
        ListNode Node4 = new ListNode(1);
        Node1.next = Node2;
        Node2.next = Node3;
        Node3.next = Node4;
        System.out.println(test.isPalindrome(Node1));
    }
}
