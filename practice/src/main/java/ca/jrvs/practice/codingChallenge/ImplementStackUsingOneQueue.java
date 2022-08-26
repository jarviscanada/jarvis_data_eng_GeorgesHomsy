package ca.jrvs.practice.codingChallenge;


import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class ImplementStackUsingOneQueue {

  private Queue<Integer> q1 = new LinkedList();

  public Queue<Integer> getQ1() {
    return q1;
  }

  public void setQ1(Queue<Integer> q1) {
    this.q1 = q1;
  }

  /**
   * Time Complexity: O(1)
   * Space Complexity: O(1)
   * @param x
   */
  public void push(int x) {
    q1.add(x);
    int sz = q1.size();
    while (sz > 1) {
      q1.add(q1.remove());
      sz--;
    }
  }

  /**
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   * @return
   */
  public int pop() {
    if (!q1.isEmpty()) {
      return q1.remove();
    } else {
      throw new NoSuchElementException("Empty Stack!");
    }
  }

  /**
   * Time Complexity: O(1)
   * Space Complexity: O(1)
   * @return
   */
  public int top() {
    return q1.peek() ;
  }

  /**
   * Time Complexity: O(1)
   * Space Complexity: O(1)
   * @return
   */
  public boolean empty() {
    return q1.isEmpty();
  }
}
