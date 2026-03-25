# TradeWise V2

TradeWise V2 is a full-stack paper trading platform inspired by real-world stock trading applications. It allows users to register, log in, add virtual funds, track live-simulated stock prices, buy and sell stocks, manage a portfolio, maintain a watchlist, and review transaction history. The project is also enhanced with a complete DevOps setup including Docker, Docker Compose, Spring Boot Actuator, Prometheus, Grafana, GitHub Actions, Jenkins, and Kubernetes manifests.

---

## Project Overview

The goal of TradeWise V2 is to simulate a realistic trading environment where users can experience stock trading workflows without using real money. The platform includes both application development and DevOps practices, making it suitable as a strong academic and project demonstration.

This project covers:
- Full-stack web application development
- Backend API integration
- Database connectivity
- Docker-based containerization
- CI/CD basics
- Monitoring and observability
- Kubernetes deployment manifests

---

## Features

### User Features
- User registration
- User login
- Session-based dashboard experience
- Logout

### Trading Features
- Add virtual funds to wallet
- View market stocks
- Buy stocks
- Sell stocks
- Real-time simulated stock price changes
- Portfolio tracking
- Transaction history
- Watchlist management

### Dashboard Features
- Wallet summary
- Portfolio value
- Profit/Loss visibility
- Recent transaction activity
- Top market movers

### Watchlist Features
- Add stock to watchlist
- Remove stock from watchlist
- Monitor saved stocks with backend integration

---

## Tech Stack

### Frontend
- HTML
- CSS
- JavaScript

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL

### Build Tool
- Maven

### DevOps / Monitoring
- Git
- GitHub
- Docker
- Docker Compose
- Spring Boot Actuator
- Prometheus
- Grafana
- GitHub Actions
- Jenkins
- Kubernetes

---

## Project Structure

```text
TradeWise V2/
│
├── backend/
│   ├── src/main/java/com/tradewisev2/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── TradeWiseV2Application.java
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── Dockerfile
│   └── pom.xml
│
├── frontend/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── funds.html
│   ├── market.html
│   ├── portfolio.html
│   ├── transactions.html
│   ├── watchlist.html
│   ├── live-track.html
│   └── Dockerfile
│
├── docker/
│   ├── prometheus.yml
│   └── grafana-dashboard.json
│
├── jenkins/
│   ├── Dockerfile
│   └── init.groovy.d/
│       └── reset-admin.groovy
│
├── k8s/
│   ├── backend-deployment.yaml
│   ├── backend-service.yaml
│   ├── frontend-deployment.yaml
│   ├── frontend-service.yaml
│   ├── mysql-deployment.yaml
│   ├── mysql-service.yaml
│   ├── prometheus-deployment.yaml
│   ├── prometheus-service.yaml
│   ├── grafana-deployment.yaml
│   └── grafana-service.yaml
│
├── .github/workflows/
│   └── ci.yml
│
├── docker-compose.yml
└── README.md