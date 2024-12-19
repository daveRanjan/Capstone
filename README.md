<div align="center">
  <h1>Capstone Project - E-commerce Backend Architecture</h1>
  <p>
    Scalable, efficient, and secure e-commerce platform backend built with modern technologies and best practices.
  </p>
  <a href="https://github.com/daveRanjan/Capstone"><strong>Explore the Repository »</strong></a>
</div>

---

## 🚀 About the Project

This project demonstrates the implementation of a comprehensive backend architecture for an e-commerce platform. It focuses on scalability, security, and performance, ensuring a seamless user experience.

### Key Features

- **User Management**: User registration, login, profile updates, and secure password reset.
- **Product Catalog**: Advanced search and filter capabilities powered by Elasticsearch.
- **Cart & Checkout**: Dynamic cart management and smooth checkout flow.
- **Order Management**: Order tracking, history, and notifications.
- **Payment Integration**: Secure transactions with multiple payment gateways.
- **Microservices Architecture**: Modular and scalable design for independent service deployment.

---

## 🛠️ Built With

### Backend Technologies
- **Java 21** with **Spring Boot 3.4.0**
- **Spring Cloud** for microservice orchestration
- **MySQL**, **MongoDB (DocumentDB)**, **Redis**, and **Elasticsearch** for data storage and caching
- **Apache Kafka** for inter-service communication

### DevOps & Infrastructure
- **AWS ECS**, **RDS**, **VPC**, **ALB**, **CloudWatch**
- **Docker** for containerization
- **GitHub Actions** for CI/CD

---

## 🏗️ Architecture Overview

### Microservices
- **User Service**: Authentication and profile management
- **Product Service**: Catalog management and search
- **Cart Service**: Cart operations
- **Order Service**: Order processing
- **Payment Service**: Payment gateway integration

### Communication
- **REST APIs** for synchronous communication
- **Kafka** for asynchronous communication and event-driven architecture

### Deployment
- AWS ECS with Fargate for container orchestration
- Multi-AZ RDS for database high availability
- Prometheus and Grafana for monitoring and alerting

---

## 📖 Getting Started

### Prerequisites
- **Java 21**
- **Docker**
- **AWS Account** for cloud deployment
- **MySQL** and **Elasticsearch** setup

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/daveRanjan/Capstone.git
   cd Capstone
