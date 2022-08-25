package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-02c9274a55d0406c989363f75d794356
 */
public class StringToInteger {

  /**
   * Time complexity: O(1)
   * @param s
   * @return
   */
  public static int myAtoi(String s) {
    if (s.length()<0 || s.length()>200) {
      throw new IllegalArgumentException("Length of the String should be 0 and 200");
    }

    return Integer.parseInt(s.replaceAll("[+]\\s|[-]\\s|[~!@#$%^&*()_{}\\[\\]:;,.<>/?]|[A-Za-z]","").trim());
  }

  /**
   * Time complexity: O(n)
   * @param s
   * @return
   */
  public static int myAtoiAscii(String s) {
    int total = 0;
    int sign = 1;
    int numberAsciiStart = '0';
    int numberAsciiEnd = '9';

    if (s.length()<0 || s.length()>200) {
      throw new IllegalArgumentException("Length of the String should be 0 and 200");
    }

   for (char c: s.toCharArray()) {
     int letter = c;

     if ((letter >= numberAsciiStart && letter <= numberAsciiEnd)) {
       total  = total*10 + (letter - '0');
     }

     if(((c == '+') || (c == '-'))) {
      sign = c == '+' ?  1 : -1;
     }
   }
   return total*sign;
  }
}
