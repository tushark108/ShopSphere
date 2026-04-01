# 🛒 ShopSphere – E-commerce Microservices Platform

A scalable **E-commerce platform (Amazon-like)** built using **Microservices Architecture** and **Docker**, designed to simulate real-world enterprise systems.

---

## 🚀 Tech Stack

### Frontend

* Angular

### Backend

* Spring Boot (Microservices)
* Spring Cloud Gateway (API Gateway)

### Database & Cache

* PostgreSQL
* Redis

### Messaging

* Apache Kafka

### DevOps & Infrastructure

* Docker
* Docker Compose
* Nginx (Reverse Proxy)

### Monitoring & Logging

* Prometheus + Grafana
* ELK Stack (Elasticsearch, Logstash, Kibana)

---

## 🏗️ Architecture
```
Angular Frontend
        |
      Nginx
        |
    API Gateway
        |
-------------------------------------------------
|        |         |        |        |           |
User   Product    Cart    Order   Payment   Notification
Service Service   Service Service  Service     Service
        |
       Kafka
        |
PostgreSQL + Redis
        |
Monitoring + Logging
```

---

## 📦 Microservices

| Service              | Description                      |
| -------------------- | -------------------------------- |
| User Service         | Authentication & user management |
| Product Service      | Product catalog & search         |
| Cart Service         | Shopping cart operations         |
| Order Service        | Order creation & tracking        |
| Payment Service      | Payment processing               |
| Notification Service | Email/SMS notifications          |
| API Gateway          | Central routing                  |

---

## ⚙️ Features

* User Registration & Login (JWT based)
* Product Browsing & Search
* Add to Cart / Remove from Cart
* Order Placement
* Payment Simulation
* Event-driven communication using Kafka
* Caching using Redis
* Centralized Logging (ELK)
* Monitoring with Prometheus & Grafana
* Fully Dockerized system

---

## 🐳 Running the Project

### 1️⃣ Clone the Repository

```
git clone https://github.com/your-username/shopsphere-ecommerce-platform.git
cd shopsphere-ecommerce-platform
```

---

### 2️⃣ Start All Services

```
docker compose up --build
```

---

### 3️⃣ Access Applications

| Service     | URL                   |
| ----------- | --------------------- |
| Frontend    | http://localhost:4200 |
| API Gateway | http://localhost:8080 |
| Grafana     | http://localhost:3000 |
| Kibana      | http://localhost:5601 |

---

## 📁 Project Structure

```
shopsphere-ecommerce-platform
│
├── frontend-angular
├── services
│   ├── user-service
│   ├── product-service
│   ├── cart-service
│   ├── order-service
│   ├── payment-service
│   └── notification-service
│
├── infrastructure
│   ├── nginx
│   ├── kafka
│   ├── postgres
│   ├── redis
│   ├── monitoring
│   └── logging
│
├── docker-compose.yml
└── .env
```

---

## 🔥 Key Learnings

* Microservices Architecture
* Docker & Container Orchestration
* Event-driven systems using Kafka
* Caching strategies with Redis
* Monitoring & Logging in production systems
* API Gateway pattern
* Real-world debugging scenarios

---

## 🧪 Future Enhancements

* Kubernetes deployment
* CI/CD pipeline (GitHub Actions)
* Payment gateway integration (Stripe/Razorpay)
* Role-based authentication
* Cloud deployment (AWS/GCP)

---

## 👨‍💻 Author

**Tushar Kshirsagar**

* Java Full Stack Developer
* Passionate about Microservices, Docker & AI

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
