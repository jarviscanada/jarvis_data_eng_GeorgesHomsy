package ca.jrvs.practice.codingChallenge;

class ListNode {
   int val;
   ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }

  @Override
  public String toString() {
    return "value: " + val;
  }
}

public class RemoveNthNodeFromEndOfList {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode current;
    ListNode removeNode = null;
    current = head;
    int i = 1;
    int nthElement;

//    if (head.next ==null) {
//      return head;
//    }

    while (current.next != null) {
      current = current.next;
      i++;
    }

    nthElement = i - n;
    current = head;
    i = 1;

    while (current.next != null) {
      if( i == nthElement) {
        removeNode = current.next;
        current.next = current.next.next;
      } else {
        current = current.next;
      }
      i++;
    }

    return removeNode;
  }
}
