FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
# Copy the entire project for multi-module build
COPY .. .
WORKDIR /build/eureka-server
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /build/eureka-server/target/eureka-server-1.0.0.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
