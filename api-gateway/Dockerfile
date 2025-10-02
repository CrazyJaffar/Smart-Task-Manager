
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
# Copy the entire project for multi-module build
COPY .. .
WORKDIR /build/api-gateway
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /build/api-gateway/target/api-gateway-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


