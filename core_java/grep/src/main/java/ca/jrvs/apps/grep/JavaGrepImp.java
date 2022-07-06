package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep{

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    for (File f:listFiles(this.rootPath)){
      for (String line:readLines(f)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }

    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    File rootFolder = new File(rootDir);
    List<File> listFile = new ArrayList<>();

    for (File f:rootFolder.listFiles()){
      if (f.isFile()) {
        listFile.add(f);
      }
    }

    return listFile;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> listLine = new ArrayList<>();
    BufferedReader br = null;

    try {
      br = new BufferedReader(new FileReader(inputFile));

      while (br.readLine() != null) {
        listLine.add(br.readLine());
      }

      br.close();
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Argument not of type File!", e);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return listLine;
  }

  @Override
  public boolean containsPattern(String line) {
    return ((Pattern.matches(this.regex,line)));
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(this.outFile));
    for (String line:lines){
      bw.write(line);
    }
    bw.close();
  }

  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.debug("Error:Unable to process",ex);
    }

  }
}
