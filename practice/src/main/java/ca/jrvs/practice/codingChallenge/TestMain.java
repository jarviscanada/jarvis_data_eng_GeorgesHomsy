package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import sun.awt.X11.XSystemTrayPeer;

public class TestMain {

  public static void main(String[] args) {
    RemoveNthNodeFromEndOfList r = new RemoveNthNodeFromEndOfList();

    ListNode l1 = new ListNode(1);
    ListNode l2 = new ListNode(2);
    ListNode l3 = new ListNode(3);
    ListNode l4 = new ListNode(4);
    ListNode l5 = new ListNode(5);

    l1.next = l2;
    l2.next = l3;
    l3.next = l4;
    l4.next = l5;

    System.out.println(r.removeNthFromEnd(l1,8));


  }
}