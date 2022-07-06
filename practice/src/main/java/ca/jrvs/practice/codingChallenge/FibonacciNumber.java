package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-47ec570fd0f0469f84c9691b00c43205
 */
public class FibonacciNumber {

  /**
   * Big O: Time complexity -> O(2^N)
   *  `     Space complexity -> O(n)
   * @param n
   * @return
   */
  public int fibonacciRecursion (int n) {
    if(n==0)
      return 0;
    if(n==1)
      return 1;
    return fibonacciRecursion(n-1)+fibonacciRecursion(n-2);
  }

  /**
   * Big O: Time complexity -> O(n)
   *  `     Space complexity -> O(1) -> no dynamic space needed
   * @param n
   * @return
   */
  public int fibonacciDP(int n) {
    if(n==0)
      return 0;
    if(n==1)
      return 1;

    int fb = 1;
    int x1= 1;
    int x2 = 0;

    for (int i = 2; i <= n; i++) {
      fb = x1+x2;
      x2 = x1;
      x1 = fb;
    }

    return fb;
  }
}
