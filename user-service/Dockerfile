FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/user-service-*.jar app.jar

# User service port
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
