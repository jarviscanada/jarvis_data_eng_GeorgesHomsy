package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrintLetterWithNumberTest {
  PrintLetterWithNumber p;
  @Before
  public void setUp() throws Exception {
    p = new PrintLetterWithNumber();
  }

  @Test
  public void printLetterWithNumberEmptyString() {
    String expected = "Invalid Input!";
    String actual = p.printLetterWithNumber("");

    assertEquals(expected,actual);
  }

  @Test
  public void printLetterWithNumber() {
    String expected = "a1b2c3A27B28";
    String actual = p.printLetterWithNumber("abcAB");

    assertEquals(expected,actual);
  }

  @Test
  public void printLetterWithNumberEscapeWhiteSpaces() {
    String expected = "a1b2c3A27B28";
    String actual = p.printLetterWithNumber("abc AB");

    assertEquals(expected,actual);
  }
}