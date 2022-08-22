FROM openjdk:8-alpine
ENV CONSUMER_KEY ""
ENV CONSUMER_SECRET ""
ENV ACCESS_TOKEN ""
ENV TOKEN_SECRET ""
COPY target/java_apps*.jar /usr/local/app/twitter/lib/twitter.jar
ENTRYPOINT ["java","-jar","/usr/local/app/twitter/lib/twitter.jar"]
