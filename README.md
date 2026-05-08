# 🚀 Digital Asset Lending System — Backend

Enterprise-grade backend service for managing digital asset lending workflows within organizations.

Built using Spring Boot with secure JWT authentication, role-based authorization, department-level validation, and complete lending lifecycle management.

---

## ✨ Overview

The system enables organizations to efficiently manage software licenses and digital resources through a centralized platform.

### Core Capabilities

- 🔐 Secure authentication & authorization
- 📦 Digital asset management
- 📄 Lending request workflow
- 🏢 Department-based approval system
- 📊 License availability tracking
- 👥 Role-based access control

---

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| ☕ Java 17 | Core language |
| 🌱 Spring Boot | Backend framework |
| 🛡️ Spring Security | Authentication & authorization |
| 🔑 JWT | Token-based authentication |
| 🗄️ MySQL | Database |
| 🔄 Hibernate / JPA | ORM |
| 📦 Maven | Dependency management |

---

## 🏗️ Architecture

```bash
src/main/java/com/project/DigitalAssetLendingSystem
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
└── service
```

---

## 👤 Roles & Access

### 🔴 ADMIN
- Manage digital assets
- View all lending records
- System-level access

### 🟠 MANAGER
- Approve or reject lending requests
- Department-level authorization

### 🟢 USER
- Request digital assets
- Return borrowed assets
- View personal lending history

---

## 🔐 Security Features

- JWT-based authentication
- BCrypt password encryption
- Stateless authorization
- Method-level security
- Role-based access control
- Department-level validation
- Protected REST APIs

---

## ⚙️ Core Functionalities

### 🔑 Authentication
- User registration
- Secure login
- Token-based session management

### 📦 Asset Management
- Asset creation
- License tracking
- Availability management

### 📄 Lending Workflow
- Asset request submission
- Approval and rejection handling
- Asset return management
- Lending history tracking

---

## 🗄️ Database Configuration

Configure MySQL inside `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digital_asset_lending
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Running Locally

### 📥 Clone Repository

```bash
git clone https://github.com/yourusername/digital-asset-lending-backend.git
```

### 📂 Navigate to Project

```bash
cd digital-asset-lending-backend
```

### 📦 Install Dependencies

```bash
mvn clean install
```

### ▶️ Run Application

```bash
mvn spring-boot:run
```

Backend runs on:

```bash
http://localhost:9000
```

---

## 📈 Future Enhancements

- 🔄 Refresh token mechanism
- 🐳 Docker containerization
- ⚡ CI/CD automation
- 📚 Swagger/OpenAPI documentation
- 📧 Email notification service
- 🚀 Redis caching
- 📜 Audit logging
- ☸️ Kubernetes deployment
- 📊 Monitoring & observability

---

## 👨‍💻 Author

### Manu SH
Java Backend Developer | Spring Boot Developer | DevOps Learner
