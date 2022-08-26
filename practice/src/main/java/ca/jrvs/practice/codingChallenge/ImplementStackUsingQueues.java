package ca.jrvs.practice.codingChallenge;


import com.sun.org.apache.bcel.internal.generic.ATHROW;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class ImplementStackUsingQueues {

  private Queue<Integer> q1 = new LinkedList();
  private Queue<Integer> q2 = new LinkedList();

  private int top;


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
    top = x;
  }

  /**
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   * @return
   */
  public int pop() {
    if (!q1.isEmpty()) {
      while (q1.size() > 1) {
        top = q1.remove();
        q2.add(top);
      }

      Queue<Integer> q = q1;
      q1 = q2;
      q2 = q;

      return q.remove();
    } else {
      top = Integer.MAX_VALUE;
      throw new NoSuchElementException("Empty Stack!");
    }
  }

  /**
   * Time Complexity: O(1)
   * Space Complexity: O(1)
   * @return
   */
  public int top() {
    return top ;
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
