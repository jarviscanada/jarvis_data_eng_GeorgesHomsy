package ca.jrvs.practice.codingChallenge;

public class RotateString {
  public boolean rotateString(String s, String goal) {
    if (s == "") {
      return false;
    }

    if (goal == "") {
      return true;
    }

    String ss = s + s;
    return ss.contains(goal) ? true : false;
  }
}
