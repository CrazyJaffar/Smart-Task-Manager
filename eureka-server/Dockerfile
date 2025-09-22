# Use official OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built jar
COPY target/eureka-server-*.jar app.jar

# Eureka default port
EXPOSE 8761

# Run the service
ENTRYPOINT ["java", "-jar", "app.jar"]
