FROM openjdk:17
EXPOSE 8080
ADD target/quotes-handler.jar quotes-handler.jar
ENTRYPOINT ["java", "-jar", "/quotes-handler.jar","--spring.profiles.active=prod","--server.port=8080"]
