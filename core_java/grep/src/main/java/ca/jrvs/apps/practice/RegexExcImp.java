package ca.jrvs.apps.practice;

import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  /**
   * @param filename 
   * @return
   */
  @Override
  public boolean matchJpeg(String filename) {
    return ((Pattern.matches(".*.(?i)(jpeg|jpg)$",filename)));
  }

  /**
   * @param ip 
   * @return
   */
  @Override
  public boolean matchIp(String ip) {
    return ((Pattern.matches("(\\d{1,3}\\.){3}+(\\d{1,3})",ip)));
  }

  /**
   * @param line 
   * @return
   */
  @Override
  public boolean isEmptyLine(String line) {
    return ((Pattern.matches("^\\s*$",line)));
  }
}
