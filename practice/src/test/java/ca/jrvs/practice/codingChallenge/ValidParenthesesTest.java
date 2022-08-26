package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidParenthesesTest {
  ValidParentheses vp;

  @Before
  public void setUp() throws Exception {
    vp = new ValidParentheses();
  }

  @Test
  public void isValid() {
    boolean actual = vp.isValid("()");
    boolean expected = true;
    assertEquals(expected,actual);
  }

  @Test
  public void isValidStringIsNull() {
    boolean actual = vp.isValid("");
    boolean expected = false;
    assertEquals(expected,actual);
  }

  @Test
  public void isValidStartWithWrongCharacter() {
    boolean actual = vp.isValid("}{");
    boolean expected = false;
    assertEquals(expected,actual);
  }
}