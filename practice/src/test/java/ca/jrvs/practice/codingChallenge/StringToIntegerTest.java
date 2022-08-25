package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringToIntegerTest {
  StringToInteger s;

  @Before
  public void setUp() {
    s = new StringToInteger();
  }
  @Test
  public void myAtoi() {
    String var = "   -42";
    int actual = s.myAtoi(var);
    int expected = -42;

    assertEquals(expected,actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void myAtoiExceedTwoHundred() {
    String var = "Lorem ipsum dolor 43 sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.";
    int actual = s.myAtoi(var);
    int expected = 43;

    assertEquals(expected,actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void myAtoiZeroLengthString() {
    String var = "";
    int actual = s.myAtoi(var);
    int expected = 43;

    assertEquals(expected,actual);
  }
}