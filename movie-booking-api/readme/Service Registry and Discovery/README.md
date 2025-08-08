# 🔧Service Registry and Discovery – Spring Boot Microservices

## 🎯 Objective
Hands-on with **Eureka Server** for **Service Registry and Discovery** in Spring Boot microservices.

📝 Info: All microservices will **register with Eureka Server** and be **discoverable via API Gateway**.

---

## 📦 Microservices Overview

1. **eurekaserver**
2. **apigateway**
3. **movieservice**
4. **theatreservice**
5. **userservice**

---

## 🚀 1. Eureka Server Setup

### 🔧 Dependencies
- Spring Web
- Eureka Server

### 🔨 Main Class
```java
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

### ⚙️ application.properties
```properties
server.port=8761

# Eureka server configs (standalone)
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

---

## 🧩 2. Movie, Theatre, User Services

### 🔧 Dependency
- Eureka Discovery Client

✅ `@EnableEurekaClient` is **not needed in latest Spring Boot** (auto-enabled via classpath detection).

### ⚙️ application.properties
```properties
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
server.port=8081  # Use different port for each service
```

---

## 🌐 3. API Gateway Setup

### 🔧 Dependencies
- Reactive Gateway (WebFlux-based, non-blocking)
- Eureka Discovery Client

### ⚙️ Configuration Options

#### Option A: application.properties
```properties
#spring.cloud.gateway.routes[0].id=movie-service
#spring.cloud.gateway.routes[0].uri=lb://movie-service
#spring.cloud.gateway.routes[0].predicates[0]=Path=/movies/**
```

#### Option B: application.yml
```yaml
spring:
  application:
    name: apigateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true  # Enables dynamic route registration from Eureka
      routes:
        - id: movie-service
          uri: lb://movieservice
          predicates:
            - Path=/movies/**
        - id: theatre-service
          uri: lb://theaterservice
          predicates:
            - Path=/theatres/**
        - id: user-service
          uri: lb://userservice
          predicates:
            - Path=/users/**

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

server:
  port: 8080
```

> 🔸 Note: `.properties` takes precedence if both `.properties` and `.yml` exist in same location.

---

## 🧪 Run and Test

### Steps
1. Start **Eureka Server** first
2. Then start **API Gateway**
3. Then start **Movie, Theatre, and User Services**

✅ Visit:  
🔗 [http://localhost:8761/](http://localhost:8761/)  
to confirm all services are registered and visible.

---

## 💡 Key Takeaways

| Module           | Important Dependencies                                 |
|------------------|--------------------------------------------------------|
| **Eureka Server**| `spring-web`, `eureka-server`                          |
| **API Gateway**  | `spring-cloud-starter-gateway`, `discovery-client`     |
| **Services**     | `discovery-client`, `spring-web`, `spring-data-jpa`, `h2-database`, `devtools` |

---

## 📌 Commit Info

> ✅ Commit includes config setup for Eureka, API Gateway, and service registration.

🔗 [GitHub Commit: 0e084db](https://github.com/shiva909/java-microservices/commit/0e084db6e2f557d835991e93f238581d14019f17)