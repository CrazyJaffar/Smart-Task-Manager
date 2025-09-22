FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/task-service-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]

