package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class ImplementStackUsingOneQueueTest {
  ImplementStackUsingQueues i;

  @Before
  public void setUp() throws Exception {
    i = new ImplementStackUsingQueues();
  }

  @Test
  public void push() {
    int expected = 3;
    i.push(3);
    assertNotNull(i.getQ1());
    assertEquals(expected,i.top());
  }

  @Test(expected = NoSuchElementException.class)
  public void popEmptyStack() {
    int actual = i.pop();
    int expected = 3;

    assertEquals(expected,actual);
  }

  @Test
  public void pop() {
    i.push(3);
    i.push(12);
    i.push(33333);
    i.push(3454);
    i.push(88888);

    int actual = i.pop();
    int expected = 88888;

    assertEquals(expected,actual);
  }

  @Test
  public void topEmptyStack() {
    int actual = i.top();
    int expected = 0;

    assertEquals(expected,actual);
  }

  @Test
  public void topWithOnlyPush() {

    i.push(3);
    i.push(12);


    int actual = i.top();
    int expected = 12;

    assertEquals(expected,actual);
  }

  @Test
  public void topWithPop() {

    i.push(3);
    i.push(12);
    i.pop();


    int actual = i.top();
    int expected = 3;

    assertEquals(expected,actual);
  }


  @Test
  public void empty() {
    i.push(3);
    assertFalse(i.empty());
  }
}