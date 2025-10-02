# Use official OpenJDK 17 image
# Use official OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built jar with exact filename
COPY target/eureka-server-1.0.0.jar app.jar

# Eureka default port
EXPOSE 8761

# Run the service
ENTRYPOINT ["java", "-jar", "app.jar"]
