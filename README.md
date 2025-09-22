# Smart Task Manager

A production-ready Java 17 Spring Boot microservices project for managing users, tasks, and notifications, with a single-page frontend.

## Features
- Microservices architecture (User, Task, Notification)
- Service discovery via Eureka
- API Gateway with CORS
- REST APIs with OpenAPI/Swagger docs
- Spring Data JPA with H2 (in-memory DB)
- Basic authentication
- Unit tests (JUnit + Mockito)
- Dockerfiles for each service
- Frontend (HTML/CSS/JS) for task management

## Project Structure
- `eureka-server/` - Service discovery
- `api-gateway/` - API Gateway
- `user-service/` - User registration & login
- `task-service/` - Task CRUD
- `notification-service/` - Logs notifications
- `frontend/` - Single-page UI

## Setup & Run (Local)
1. **Build all services:**
   ```sh
   mvn clean package -DskipTests
   ```
2. **Start Eureka Server:**
   ```sh
   cd eureka-server
   java -jar target/eureka-server-*.jar
   ```
3. **Start API Gateway:**
   ```sh
   cd api-gateway
   java -jar target/api-gateway-*.jar
   ```
4. **Start User, Task, Notification Services:**
   ```sh
   cd user-service && java -jar target/user-service-*.jar
   cd task-service && java -jar target/task-service-*.jar
   cd notification-service && java -jar target/notification-service-*.jar
   ```
5. **Open Frontend:**
   - Open `frontend/index.html` in your browser, or serve with a static server (e.g. `npx serve .`)

## Example REST API Calls
- **Register User:**
  ```sh
  curl -X POST "http://localhost:8080/users/register?username=test&password=pass"
  ```
- **Login (Basic Auth):**
  ```sh
  curl -u test:pass "http://localhost:8080/users/me?username=test"
  ```
- **Create Task:**
  ```sh
  curl -X POST "http://localhost:8080/tasks" -H "Content-Type: application/json" -d '{"title":"Task1","description":"Desc","completed":false}'
  ```
- **Get Tasks:**
  ```sh
  curl "http://localhost:8080/tasks"
  ```

## Deployment
- **Backend:** Deploy each service using Dockerfiles on Render/Heroku.
- **Frontend:** Deploy `frontend` directory to GitHub Pages.

## Documentation
- Swagger UI available at `/swagger-ui.html` for each service.

## Testing
- Run unit tests:
  ```sh
  mvn test
  ```

## Notes
- All backend services must be running for full functionality.
- CORS is enabled for frontend-backend communication.
- H2 database is in-memory; data resets on restart.

---

Enjoy using Smart Task Manager!

