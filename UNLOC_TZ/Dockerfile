FROM openjdk:latest
ADD target/timezone-api.jar timezone-api.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "timezone-api.jar"]