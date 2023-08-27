FROM openjdk:17-alpine

EXPOSE 8080

ADD target/spring-template-0.0.1-SNAPSHOT.jar spring-template-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/spring-template-0.0.1-SNAPSHOT.jar"]
