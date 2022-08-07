FROM openjdk:11
EXPOSE 8085
ADD target/autocatch-0.0.1.jar autocatch-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/autocatch-0.0.1.jar"]