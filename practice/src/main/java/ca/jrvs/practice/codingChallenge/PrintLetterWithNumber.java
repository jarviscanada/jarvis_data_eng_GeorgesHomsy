package ca.jrvs.practice.codingChallenge;

public class PrintLetterWithNumber {
  public String printLetterWithNumber (String s) {
    String newString = "";

    if (s.length() == 0) {
      return "Invalid Input!";
    }

    for (int i = 0 ; i < s.length() ; i++) {
      int asciiNumber = s.charAt(i);

      if (asciiNumber >= 97 && asciiNumber <= 122) {
        asciiNumber = s.charAt(i) - 97 + 1;
        newString = newString + s.charAt(i) + asciiNumber;
      } else if (asciiNumber >= 65 && asciiNumber <= 90) {
        asciiNumber = s.charAt(i) - 65 + 27;
        newString = newString + s.charAt(i) + asciiNumber;
      }
    }
    return newString;
  }
}
