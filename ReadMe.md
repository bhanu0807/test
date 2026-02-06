# Spring Boot Microservice

A production-ready Java 17 Spring Boot microservice with Thymeleaf UI, comprehensive testing, Docker containerization, and Kubernetes deployment configuration.

## Overview

This microservice demonstrates a complete enterprise application setup with:
- **Web UI** using Thymeleaf template engine
- **RESTful endpoints** with Spring MVC
- **Health monitoring** via Spring Boot Actuator
- **Comprehensive testing** (Unit + Integration tests)
- **Containerization** with Docker
- **CI/CD pipeline** using Jenkins
- **Kubernetes deployment** with health probes

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.2.5 |
| Build Tool | Maven |
| Template Engine | Thymeleaf |
| Testing | JUnit 5, Mockito |
| Containerization | Docker (eclipse-temurin:17-jre) |
| Orchestration | Kubernetes |
| CI/CD | Jenkins |
| Monitoring | Spring Boot Actuator |

---

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker (optional)
- Kubernetes cluster (optional)

### 1. Build & Run Locally
```bash
# Navigate to project directory
cd spring-boot-microservice

# Build the application
mvn clean package

# Run the application
java -jar target/app.jar
```

Access the application at: **http://localhost:8080**

### 2. Run Tests

```bash
# Run all tests
mvn test

# Run with coverage
mvn clean verify
```

### 3. Run with Docker

```bash
# Build Docker image
docker build -t spring-boot-microservice .

# Run container
docker run -p 8080:8080 spring-boot-microservice
```

### 4. Deploy to Kubernetes

```bash
# Apply Kubernetes manifests
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

# Check deployment status
kubectl rollout status deployment/spring-boot-microservice

# Check running pods
kubectl get pods -l app=spring-boot-microservice
```

---

## Project Structure

```
spring-boot-microservice/
├── pom.xml                                 # Maven configuration
├── Dockerfile                              # Docker build instructions
├── Jenkinsfile                             # CI/CD pipeline
├── README.md                               # This file
├── k8s/
│   ├── deployment.yaml                     # Kubernetes deployment
│   └── service.yaml                        # Kubernetes service
└── src/
    ├── main/
    │   ├── java/com/example/microservice/
    │   │   ├── MicroserviceApplication.java    # Main application class
    │   │   ├── controller/
    │   │   │   └── HomeController.java         # Web controller
    │   │   └── service/
    │   │       └── MessageService.java         # Business logic
    │   └── resources/
    │       ├── application.yml                 # Main configuration
    │       ├── templates/
    │       │   └── home.html                   # Thymeleaf template
    │       └── static/css/
    │           └── style.css                   # CSS styles
    └── test/
        ├── java/com/example/microservice/
        │   ├── MicroserviceApplicationTests.java  # Integration tests
        │   ├── controller/
        │   │   └── HomeControllerTest.java        # Controller unit tests
        │   └── service/
        │       └── MessageServiceTest.java        # Service unit tests
        └── resources/
            └── application-test.yml               # Test configuration
```

---

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Home page with input form |
| `/submit` | POST | Submit user input and display response |
| `/actuator/health` | GET | Health check endpoint |

---

## Application Features

### 🎨 Web UI
- Clean, responsive Thymeleaf-based interface
- Form input with validation
- Dynamic response display
- Modern CSS styling

### 🏗️ Architecture
- **Controller Layer**: Handles HTTP requests
- **Service Layer**: Contains business logic
- **Separation of Concerns**: Clean layered architecture

### 🧪 Testing
- **Unit Tests**: MessageService, HomeController
- **Integration Tests**: Full application context
- **MockMvc**: Controller endpoint testing
- **Test Coverage**: Service logic, controller endpoints, actuator health

### 📊 Monitoring
- Spring Boot Actuator enabled
- Health endpoint exposed at `/actuator/health`
- Liveness and readiness probes configured

### 🐳 Containerization
- Multi-stage Docker build (optional)
- Based on `eclipse-temurin:17-jre` (lightweight)
- Port 8080 exposed
- Health checks configured

### ☸️ Kubernetes Ready
- Deployment with 1 replica (scalable)
- ClusterIP service
- Liveness probe (30s delay, 10s interval)
- Readiness probe (15s delay, 5s interval)
- Resource limits configured

---

## Configuration

### Application Configuration (`application.yml`)

```yaml
server:
  port: 8080

spring:
  application:
    name: spring-boot-microservice
  thymeleaf:
    cache: false

management:
  endpoints:
    web:
      exposure:
        include: health
  endpoint:
    health:
      show-details: always
```

### Test Configuration (`application-test.yml`)

```yaml
server:
  port: 0  # Random port for testing

spring:
  application:
    name: spring-boot-microservice-test
  thymeleaf:
    cache: false

logging:
  level:
    com.example.microservice: DEBUG
```

---

## CI/CD Pipeline

The Jenkinsfile defines a complete CI/CD pipeline:

1. **Checkout** - Clone source code
2. **Build** - Compile and package (`mvn clean package`)
3. **Test** - Run unit and integration tests
4. **Docker Build** - Create container image
5. **Docker Push** - Push to registry
6. **Deploy** - Deploy to Kubernetes cluster

### Jenkins Setup Requirements

```groovy
// Add Jenkins credentials:
- docker-registry-credentials (Username + Password)

// Update environment variables in Jenkinsfile:
DOCKER_REGISTRY = 'your-registry.example.com'
```

---

## Development

### Run in Development Mode

```bash
# Run with Spring Boot Maven plugin
./mvnw spring-boot:run

# Or on Windows
mvnw.cmd spring-boot:run
```

### Run Tests in Watch Mode

```bash
# Run tests continuously
mvn test -Dspring-boot.run.fork=false
```

### Build Without Tests

```bash
mvn clean package -DskipTests
```

---

## Docker Commands

```bash
# Build image
docker build -t spring-boot-microservice:1.0.0 .

# Run container
docker run -d -p 8080:8080 --name microservice spring-boot-microservice:1.0.0

# View logs
docker logs -f microservice

# Stop container
docker stop microservice

# Remove container
docker rm microservice
```

---

## Kubernetes Commands

```bash
# Deploy application
kubectl apply -f k8s/

# Get deployment status
kubectl get deployment spring-boot-microservice

# Get pods
kubectl get pods

# View logs
kubectl logs -f <pod-name>

# Port forward to local machine
kubectl port-forward service/spring-boot-microservice 8080:80

# Scale deployment
kubectl scale deployment spring-boot-microservice --replicas=3

# Delete deployment
kubectl delete -f k8s/
```

---

## Health Checks

### Application Health

```bash
curl http://localhost:8080/actuator/health
```

**Response:**
```json
{
  "status": "UP"
}
```

---

## Testing

### Unit Tests
- `MessageServiceTest.java` - Tests business logic
- `HomeControllerTest.java` - Tests controller endpoints (MockMvc)

### Integration Tests
- `MicroserviceApplicationTests.java` - Tests full application context

### Test Coverage
```bash
# Generate coverage report (requires JaCoCo plugin)
mvn clean test jacoco:report
```

---

## Troubleshooting

### Application won't start

**Issue**: Port 8080 already in use

**Solution**:
```bash
# Find process using port 8080
netstat -ano | findstr :8080  # Windows
lsof -i :8080                  # Linux/Mac

# Kill the process or change port in application.yml
```

### Docker build fails

**Issue**: JAR file not found

**Solution**:
```bash
# Build the application first
mvn clean package

# Then build Docker image
docker build -t spring-boot-microservice .
```

### Kubernetes pod not starting

**Issue**: Image pull error

**Solution**:
```yaml
# Update deployment.yaml to use local image
imagePullPolicy: Never
image: spring-boot-microservice:latest
```

---

## Production Deployment Checklist

- [ ] Update `application.yml` with production configuration
- [ ] Enable HTTPS/TLS
- [ ] Configure external database (if needed)
- [ ] Set up proper logging (ELK, Splunk, etc.)
- [ ] Configure metrics and monitoring (Prometheus, Grafana)
- [ ] Set resource limits in Kubernetes
- [ ] Configure horizontal pod autoscaling
- [ ] Set up ingress controller
- [ ] Configure secrets management
- [ ] Enable security scanning in CI/CD

---

## License

This project is provided as-is for educational and demonstration purposes.

---

## Deployment Flow

```
LOCAL PROJECT → GIT PUSH → GITHUB → JENKINS → DOCKER → KUBERNETES
```

---

## Requirements (Original Specification)

Application requirements:
- Use Java 17
- Use Spring Boot (latest 3.x)
- Use Maven as the build tool
- Application type: microservice
- Expose a web UI using Thymeleaf
- UI requirements:
  - Home page
  - One input text field
  - One submit button
  - Display a response message after submit
- Use HTML and CSS only (no React or Node.js)
- Use standard layered architecture:
  - Controller layer (@Controller / @RestController)
  - Service layer
- Externalize configuration using application.yml
- Application must run on port 8080

Testing requirements:
- Unit tests using JUnit 5
- Mock dependencies using Mockito
- Basic integration test using Spring Boot Test
- Tests must be executable with: mvn test

Build & packaging:
- Package the application as a fat JAR
- Build command: mvn clean package
- Output artifact: target/app.jar

Containerization:
- Provide a Dockerfile using eclipse-temurin:17-jre
- Container must run the JAR using java -jar
- Expose port 8080

CI/CD:
- Provide a Jenkinsfile with the following stages:
  - Checkout
  - Build
  - Test
  - Docker build
  - Docker push (use placeholder registry and credentials)
  - Deploy to Kubernetes

Kubernetes deployment:
- Provide Kubernetes YAML files:
  - Deployment with a single replica (pod)
  - Service of type ClusterIP
- Container port: 8080
- Include liveness and readiness probes using /actuator/health

Monitoring:
- Enable Spring Boot Actuator
- Expose /actuator/health endpoint

Project structure:
- Follow standard Maven project structure
- Output the full directory tree
- Provide all source files, configuration files, and YAML files

Code quality:
- Keep code simple, readable, and production-ready
- Add comments where appropriate
- Do not omit any required files

---

## Author

Created with Claude Code - AI-powered development assistant

---

## Contributing

Feel free to submit issues and enhancement requests!

---

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [Docker Documentation](https://docs.docker.com/)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [Jenkins Documentation](https://www.jenkins.io/doc/)

---

**Note**: This is a complete, production-ready microservice template. All requirements have been implemented and tested.
