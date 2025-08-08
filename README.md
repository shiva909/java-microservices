# 🎬 Movie Booking Microservices Project

Welcome to the **Movie Booking System** built using **Spring Boot Microservices Architecture**.  
This repository contains a step-by-step lab-based implementation of key microservice concepts, configurations, and integrations.

---

## 📁 Lab Index

| Lab No. | Topic | Description |
|--------|-------|-------------|
| [Lab 1](movie-booking-api/readme/Service-Registry-and-Discovery/README.md) | Service Registry & Discovery | Setup Eureka Server, API Gateway, and Microservices registration |
| [Lab 2](movie-booking-api/readme/Centralized-Configuration-Management/README.md) | Centralized Configuration | Implement Spring Cloud Config Server and Git-based config management |
---

## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Cloud (Eureka, Config Server, Gateway)
- REST APIs
- H2 / PostgreSQL
- GitHub for config repo
- Actuator for monitoring

---

## 🚀 How to Run

1. Start `eurekaserver`
2. Start `configserver`
3. Start `apigateway`
4. Start the microservices: `movieservice`, `theatreservice`, `userservice`

Each microservice will register with Eureka and fetch its configuration from the config server.

---

## 🧭 Navigation

Use the links above to access individual lab documentation.  
Each lab focuses on a single aspect of building a real-world microservices system.

---