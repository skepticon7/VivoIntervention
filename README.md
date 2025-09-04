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
Backend runs at 👉 `http://localhost:8000`

## 🌐 Frontend Setup
### Install dependencies
```bash
cd frontend/my-vivoIntervention-app
npm install
```
### Environment variables

Create a .env file in `frontend/my-vivoIntervention-app/`:

```properties
VITE_API_BACKEND_SERVER=http://localhost:8000
VITE_AZURE_CLIENT_ID=YOUR_AZURE_CLIENT_ID
```

Start the frontend
```bash
npm run dev
```

Frontend runs at 👉 `http://localhost:5173`

## 🧑‍💻 Usage

Create a Superuser (first login)
```bash
curl -X POST \
  'http://localhost:8000/api/auth/createSuperuser' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
    "firstName": "Admin",
    "lastName": "User",
    "email": "admin@vivoenergy.com",
    "hireDate": "2025-07-07",
    "technicianStatus": "ACTIVE",
    "phoneNumber": "0612345678",
    "password": "yourpassword"
  }'
```

Login

Use the created Superuser credentials in the frontend.

Manage data

Add interventions, sites, and users based on role permissions.

Export data securely to OneDrive for Power BI visualization.

### 🔐 Roles & Permissions

Superuser → Full control (manage users, sites, intervention types, exports).

Supervisor → Manage interventions and technicians, export data.

Technician → Limited access (create/view own interventions).

### 🛠️ Tech Stack

Frontend → React, Axios, Context API, MSAL.js

Backend → Java, Spring Boot, Spring Security, Spring Data JPA, JWT

Database → MySQL

Cloud → Azure Entra ID (OneDrive integration)

Tools → Git, GitHub, Maven, npm

### 📊 Power BI Integration

Exported CSV files are automatically uploaded to OneDrive, enabling seamless integration with Power BI for visualization and reporting.

### 📫 Contributing

Contributions are welcome! Please open issues or pull requests.

