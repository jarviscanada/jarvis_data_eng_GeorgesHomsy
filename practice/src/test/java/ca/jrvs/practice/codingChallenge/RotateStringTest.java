package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RotateStringTest {

  RotateString rs;

  @Before
  public void setUp() throws Exception {
    rs = new RotateString();
  }

  @Test
  public void rotateStringFirstArgumentEmpty() {
    boolean actual = rs.rotateString("", "cdeab");
    assertEquals(false,actual);
  }

  @Test
  public void rotateStringSecondArgumentEmpty() {
    boolean actual = rs.rotateString("rererer", "");
    assertEquals(true,actual);
  }

  @Test
  public void rotateString() {
    boolean actual = rs.rotateString("abcde", "cdeab");
    assertTrue(actual);
  }
}