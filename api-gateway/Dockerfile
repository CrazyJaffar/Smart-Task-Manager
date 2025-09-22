FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/api-gateway-*.jar app.jar

# API Gateway port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


