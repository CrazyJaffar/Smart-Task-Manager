FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/notification-service-*.jar app.jar

# Notification service port
EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
