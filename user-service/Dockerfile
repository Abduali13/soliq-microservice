FROM openjdk:17
ADD target/user-service.jar app.jar
VOLUME /sample.app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]