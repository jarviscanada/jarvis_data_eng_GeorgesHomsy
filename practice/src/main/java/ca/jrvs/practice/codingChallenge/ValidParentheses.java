package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
  public boolean isValid(String s) {

    if (s == "") {
      return false;
    }

    Map<Character,Character> map  = new HashMap();
    Stack<Character> stack = new Stack();

    map.put('[',']');
    map.put('{','}');
    map.put('(',')');

    for (int i = 0 ; i < s.length() ; i++) {
      char x = s.charAt(i);

      if ((x == '}' || x == ')' || x == ']') && stack.isEmpty()) {
        return false;
      }

      if ((x == '{' || x == '(' || x == '[')) {
        if (map.containsKey(x)) {
          stack.push(x);
        }
      } else if (x == '}' || x == ')' || x == ']') {
        if ((char)map.get(stack.peek()) == x) {
          stack.pop();
        }
        else {
          return false;
        }
      }
    }

    return true;
  }
}
