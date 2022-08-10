package ca.jrvs.apps.practice;

import ca.jrvs.apps.practice.RegexExcImp;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String args[]) {
    RegexExcImp r = new RegexExcImp();
    System.out.println(r.matchIp("12..123.123.123"));
    System.out.println("Hello, World");
  }
}