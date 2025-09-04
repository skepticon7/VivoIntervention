# 🛠️ IT Interventions Management System

Welcome to the **IT Interventions Management System**, a full-stack web application developed during my internship at **Vivo Energy Morocco** (IT Service, Casablanca).  
The system is designed to **track, manage, and analyze IT interventions** across all Vivo Energy sites in Morocco, with role-based access control and secure data export for **Power BI** dashboards.

---

## 🌟 Features

- 🔑 **Role-Based Access Control (RBAC)** for Superusers, Supervisors, and Technicians
- 📊 **Centralized tracking** of IT interventions across all sites
- ☁️ **CSV export to OneDrive** with **Azure Entra ID (MSAL)** authentication for Power BI analytics
- 🔐 **Secure authentication** with JWT
- ⚡ **React frontend** with caching for optimized performance
- 🗄️ **Spring Boot backend** with layered architecture and REST APIs
- 🛢️ **MySQL database** for reliable persistence

---


---

## 🚀 Getting Started

### ✅ Requirements

- ☕ Java JDK 17+
- 🐬 MySQL
- 🟩 Node.js + npm
- 🔑 Azure Entra ID credentials (Client ID, Secret, Tenant ID)
- 🐙 Git

---

### 📂 Cloning the Repository

```bash
git clone https://github.com/skepticon7/VivoIntervention.git
cd VivoIntervention
```


---

## ⚙️ Backend Setup

### Configure Database
- Create a MySQL database, e.g. `interventions_db`.

### Application Secrets
Create a file at `backend/src/main/resources/application-secrets.properties`:

```properties
spring.datasource.password=YOUR_DB_PASSWORD
application.security.jwt.secretkey=YOUR_JWT_SECRET_KEY

azure.client-id=YOUR_AZURE_CLIENT_ID
azure.client-secret=YOUR_AZURE_CLIENT_SECRET
azure.tenant-id=YOUR_AZURE_TENANT_ID
``` 
⚠️ Never commit this file to GitHub.

Run Backend
```bash
cd backend
./mvnw spring-boot:run
```
Backend runs at 👉 http://localhost:8000

