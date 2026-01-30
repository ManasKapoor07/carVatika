# 🚗 CarVatika – Backend API

Production-ready backend service for **CarVatika**, a premium automotive accessories e-commerce platform.  
Built with **Spring Boot**, secured using **JWT authentication**, and deployed on **Railway** with **MySQL**.

This service exposes REST APIs for authentication, product management, and cart-ready workflows, designed with scalability and clean architecture in mind.
## Frontend Repo
-https://github.com/ManasKapoor07/car-accessories-landing
---

## 🧱 High-Level Architecture

Client (Web / Mobile)
|
v
Spring Boot REST API
|
v
MySQL Database


- Stateless RESTful APIs  
- JWT-based authentication & authorization  
- Relational persistence using Spring Data JPA  

---

## ⚙️ Tech Stack

- **Language:** Java 17 / 21  
- **Framework:** Spring Boot 3.x  
- **Database:** MySQL  
- **ORM:** Hibernate (JPA)  
- **Security:** Spring Security + JWT  
- **Build Tool:** Maven  

---

## 🔐 Core Features

- User registration & authentication
- JWT token generation and validation
- Secure role-based API access
- Product management APIs
- Cart & order-ready domain models
- Environment-based configuration (no hardcoded secrets)
- MySQL connectivity

---

**Design principles followed:**
- Clear separation of concerns  
- Thin controllers, logic in services  
- Repository abstraction via Spring Data JPA  

---

## 🛠️ Configuration & Environment Variables

### Required (Production)

```env
SPRING_DATASOURCE_URL=
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
```
## ▶️ Running Locally
-##1️⃣ Clone the repository
- git clone https://github.com/your-username/carvatika-backend.git
- cd carvatika-backend

## 2️⃣ Configure application.properties
- spring.datasource.url=jdbc:mysql://localhost:3306/carvatika
- spring.datasource.username=root
- spring.datasource.password=yourpassword
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true

---

## 3️⃣ Run the application
-mvn spring-boot:run
---

The API will be available at:

http://localhost:8080
---

## Project Status

-Backend architecture complete
-Authentication & MySQL integration done
-Orders & payments APIs (in progress)
-Admin APIs (in progress)

<img width="1354" height="643" alt="image" src="https://github.com/user-attachments/assets/07a02b7a-f8f9-40b7-a729-85c8778e6955" />




