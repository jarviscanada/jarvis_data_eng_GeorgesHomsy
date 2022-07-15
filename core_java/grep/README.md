# Grep application

## About this project
This application is an implementation of the `grep` command of Linux. `Grep` command in linux is used to search text in a given file. The text could be a (letter/word) or a regular expression. It goes through files recursively in a directory and then return a strings that match the input pattern.
The application is written in `Java8` and I used IntelliJ as an IDE. I use `maven` as a project management tool which also help me to handle all dependencies and the automation of the project's life cycle. For the deployment I used `Docker`. This application was implemented in 2 ways: using the For/loop and using the lambda/Stream API.

### Technologies used
* Docker
* GitHub
* Apache Maven
* Git
* Java SE 8

## Quick start
To run the application we can use 2 different approach:
* Using jar file:
  * Creating the jar file:
    ```bash
      mvn clean compile package
    ```
  * Calling the jar file and passing the parameters:
    ```bash
      java -cp {jar file} {regex} {rootPath} {output file path -> result}
    ```

## Implemenation
### Pseudocode
```bash
    matchedLines = []  
    for file in listFilesRecursively(rootDir)  
    for line in readLines(file)  
    if containsPattern(line)  
    matchedLines.add(line)
    writeToFile(matchedLines)
```

### Performance Issue
The main issue was related to the use of the `List` data structure and the use of `for loop` to store files and results in these `List`. 
It uses a lot of memory to perform each tasks. So, to prevent facing these issue I used lambda and stream API.

## Test
All testing was manual. To test the app:
  1. Creating a text file containing poetry from Shakespeare. [View file](core_java/grep/data/txt/shakespeare.txt)
  2. Testing each function separately using Intellij debugger.
  3. Running the application using different arguments to handle all the expected errors.
  4. On the other hand, use the `grep` linux command with the same arguments and compare the output file from IDE and what printed in command line.
  5. For the jar file, I used `Uber/Fat Jar` in the `pom.xml` to package all the classes and the dependencies with maven.
  6. I tried to run the application using the `java -cp` command in CLI and compare the files:
     * First file: Output file from running the grep app from the IDE
     * Second file: Output file from running the grep app from CLI

## Deployment
For the deployment I used `Docker`. I create a docker image of the app, and then I pushed it to `Dockerhub` for easier distribution.
* Create a [`Dockerhub`](https://hub.docker.com/) account.
  * Create a `Dockerfile` used to build an image:
      ```bash
      cat > Dockerfile << EOF
      FROM openjdk:8-alpine
      COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
      ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
      EOF
    ```
    * Build the image using `docker build` command:
    ```bash
      docker build -t {dockerhub_username}/{image name} {path for creation}
    ```
    * Push your image to `DockerHub` using `docker push`:
    ```bash
      docker push dockerhub_username}/{image name}
    ```
    
* And now you can go to hub.docker.com and verify your image

## Improvements
1. Automate the testing using `JUnit`.
2. Adding an option to the user to choose if he wants to return along with lines the line's number.
3. Enable grep app to process huge data by replacing `list` by `steam`.