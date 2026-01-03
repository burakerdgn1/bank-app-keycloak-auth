# 🏦 Bank Application – Microservices Architecture with Keycloak

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.x-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue?style=for-the-badge&logo=postgresql)
![Keycloak](https://img.shields.io/badge/Keycloak-Enabled-red?style=for-the-badge&logo=keycloak)

A cloud-ready banking backend system built with **Spring Boot microservices**, secured using **Keycloak (OAuth2 / OpenID Connect)** and exposed through a **Spring Cloud Gateway**.

The system demonstrates centralized authentication, role-based authorization, inter-service communication, and API gateway patterns in a real-world microservices setup.

---

## 🧱 Architecture Overview

```mermaid
graph TD
    subgraph Client_Layer [Client Layer]
        ReactApp[React Frontend]
    end

    subgraph Security_Layer [Identity Provider]
        Keycloak[(Keycloak Auth)]
    end

    subgraph Gateway_Layer [Edge Server]
        Gateway[Spring Cloud Gateway]
    end

    subgraph Microservices [Backend Services]
        UserService[User Service]
        AccountService[Account Service]
        TransService[Transaction Service]
    end

    subgraph Database_Layer [Data Persistence]
        UserDB[(User DB)]
        AccDB[(Account DB)]
        TransDB[(Transaction DB)]
    end

    %% Flow
    ReactApp -->|1. Login Request| Keycloak
    Keycloak -.->|2. Access Token JWT| ReactApp
    ReactApp -->|3. API Request + JWT| Gateway
    
    Gateway -->|4. Validate Token| Keycloak
    Gateway -->|5. Route Request| UserService
    Gateway -->|5. Route Request| AccountService
    Gateway -->|5. Route Request| TransService

    %% Inter-service
    TransService -.->|OpenFeign| AccountService
    
    %% Databases
    UserService --- UserDB
    AccountService --- AccDB
    TransService --- TransDB

    %% Styles
    style Keycloak fill:#f96,stroke:#333,stroke-width:2px
    style Gateway fill:#69f,stroke:#333,stroke-width:2px
    style ReactApp fill:#61dafb,stroke:#333

```

## Authentication & Authorization (Keycloak)

Keycloak acts as the Identity Provider (IdP). The authentication flow is as follows:

1. Client authenticates via Keycloak and receives a **JWT Access Token**.
2. Client sends requests to the Gateway with  
   `Authorization: Bearer <token>` header.
3. Gateway validates the JWT using Keycloak’s **JWK endpoint**.
4. Roles are extracted and converted into **Spring Security authorities**.
5. Authorized requests are routed to the respective backend microservices.

---

## 🧩 Services Breakdown

### 👤 User Service (`user-service`)

Responsible for user management and authentication-related logic.

**Features**
- User registration
- Profile retrieval
- Spring Security integration

**Tech**
- Spring Boot
- Spring Security
- JWT
- PostgreSQL

---

### 💳 Account Service (`account-service`)

Manages bank accounts and balances.

**Features**
- Account creation
- Balance management
- Inter-service communication via OpenFeign

**Tech**
- Spring Data JPA
- OpenFeign
- PostgreSQL

---

### 💸 Transaction Service (`transaction-service`)

Handles money transfers and transaction history.

**Features**
- Transaction persistence
- OpenFeign-based communication
- Fallback handling with Resilience4j

**Tech**
- Spring Data JPA
- OpenFeign
- Resilience4j
- PostgreSQL

---

### 🚪 Gateway Server (`gateway-server`)

Single entry point for all client requests.

**Features**
- JWT validation
- Role-Based Access Control (RBAC)
- Circuit Breaker support

**Tech**
- Spring Cloud Gateway
- OAuth2 Resource Server
- Resilience4j

---

## 🗄️ Database Design

| Service             | Database Name | Purpose                    |
|---------------------|---------------|----------------------------|
| User Service        | userdb        | Credentials & Profiles     |
| Account Service     | accountdb     | Balances & Account Info    |
| Transaction Service | transactiondb | Transfer History           |

---

## ⚙️ Tech Stack

### Language & Frameworks
- Java 21
- Spring Boot 3.4.x

### Security
- Keycloak
- OAuth2
- OpenID Connect

### Cloud & Communication
- Spring Cloud Gateway
- OpenFeign
- Resilience4j

### Persistence
- PostgreSQL
- Hibernate / JPA

### Build Tool
- Maven

---

## 🚀 Running the Project

### Prerequisites
- Java 21
- Maven
- PostgreSQL
- Keycloak (Realm: `bank-app`)

### Steps
Clone the repo:

Bash

git clone [https://github.com/burakerdgn1/bank-app.git](https://github.com/burakerdgn1/bank-app.git)
Start Services (in order):

gateway-server (Port 8080)

user-service

account-service

transaction-service

### 📌 Notes

All services are secured via Keycloak JWT-based authentication.

Inter-service communication is handled using OpenFeign.

The Gateway enforces centralized security and routing.
