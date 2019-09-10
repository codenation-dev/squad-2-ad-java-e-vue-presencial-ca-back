FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/logstack-api-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]