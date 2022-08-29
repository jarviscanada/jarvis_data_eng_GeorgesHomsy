package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RemoveNthNodeFromEndOfListTest {
  RemoveNthNodeFromEndOfList r;
  ListNode l1,l2,l3,l4,l5;

  @Before
  public void setUp() throws Exception {
    r = new RemoveNthNodeFromEndOfList();

    l1 = new ListNode(1);
    l2 = new ListNode(2);
    l3 = new ListNode(3);
    l4 = new ListNode(4);
    l5 = new ListNode(5);

    l1.next = l2;
    l2.next = l3;
    l3.next = l4;
    l4.next = l5;
  }

  @Test
  public void removeNthFromEnd() {
    ListNode actual = r.removeNthFromEnd(l1,2);
    ListNode expected = l4;

    assertEquals(actual.val,expected.val);
  }

  @Test
  public void removeNthFromEndNodeNotFound() {
    ListNode actual = r.removeNthFromEnd(l1,8);
    assertNull(actual);
  }
}