# 📌 Feature: Centralized Configuration Management
🔗 [Notes:](notes/README.md)
---

## 📘 Overview
This document outlines the steps to implement centralized configuration management for microservices using **Spring Cloud Config Server** and a **Git-based repository**.

### ✅ Steps to Follow:
1. Create a Spring Boot Config Server Microservice
2. Create a Git Repository for Storing Configs
3. Test If Config Server is Working
4. Configure Microservices to Use the Config Server
5. Update Configuration Without Restarting *(Optional)*

---

## 🔧 STEP 1: Create a Spring Boot Config Server Microservice

### 1. Add the following dependencies in `pom.xml`:
- `spring-boot-starter-actuator` – For health and monitoring endpoints
- `spring-cloud-config-server` – To enable config server capabilities
- `spring-boot-starter-web` – To expose REST endpoints

### 2. Annotate main class:
```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

### 3. Configuration in `application.yml`:
```yaml
server:
  port: 8888

spring:
  application:
    name: configserver
  cloud:
    config:
      server:
        git:
          uri: https://github.com/shiva909/movie-config-repo.git
          username: ${GIT_USERNAME}
          password: ${GIT_PASSWORD}
          clone-on-start: true
        watch:
          enabled: true
```

---

## 📁 STEP 2: Create a Git Repository for Storing Configurations

### 1. Folder structure:
```
movie-config-repo/
├── movie-service.yml
├── booking-service.yml
└── application.yml
```

### Example JSON config (`user-config.json`):
```json
{
  "name": "Venkat",
  "age": 24,
  "email": "venkat07@gmail.com",
  "phone": "+91 9000000088"
}
```

**Access:**
- YAML: `http://localhost:8888/movie-service/default`
- JSON: `http://localhost:8888/user-config.json`

---

## ✅ STEP 3: Test If Config Server is Working

Start the Config Server and test via:
```
http://localhost:8888/movie-service/default
```

---

## ⚙️ STEP 4: Configure Microservices to Use the Config Server

### 1. Add dependency in each microservice:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

### 2. Update `application.yml` (for Spring Boot 2.4+):
```yaml
spring:
  application:
    name: movieservice
  config:
    import: "optional:configserver:"
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: true
```

> ⚠️ **Note:** `config.import` order is important. Avoid bootstrap.yml in new Spring Boot versions.

---

## 🔄 STEP 5: Update Configuration Without Restarting (Optional)

### Steps:
1. Add `spring-boot-starter-actuator`
2. Use `@RefreshScope` on config beans
3. Call:
```
POST http://localhost:8081/actuator/refresh
```

---

## 🎁 Bonus Notes

### 1. Set Environment Variables:
```bash
export GIT_USERNAME=your-github-username
export GIT_PASSWORD=your-password
```

### 2. Environment-Specific Configs:
Use:
- `movie-service-dev.yml`
- `movie-service-prod.yml`

Access via:
```
http://localhost:8888/movie-service/dev
```

### 3. Retry Config:
```yaml
spring:
  cloud:
    config:
      fail-fast: true
      retry:
        max-attempts: 5
        initial-interval: 1000
```

---

## 📦 Utility Class to Fetch Config (Optional)
```java
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConfigFetcherService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchUserConfig() {
        String url = "http://localhost:8888/user-config.json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
```

---

## ✅ To Explore Later
- Securing Config Server using Spring Security
- Auto-refresh with Spring Cloud Bus (Kafka/RabbitMQ)
- Profile-specific fallbacks
- Versioned config management