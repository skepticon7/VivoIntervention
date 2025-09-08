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
- 🐳 Docker containerization for easy deployment



## 🚀 Quick Start with Docker
### ✅ Prerequisites
- 🐳 Docker Engine (version 20.10.0+)
- 🐳 Docker Compose (version 2.0.0+)
- 🔑 Azure Entra ID credentials (Client ID, Secret, Tenant ID)

### 📋 Setup Instructions

1 - Clone the repository

```bash
git clone https://github.com/skepticon7/VivoIntervention.git
cd VivoIntervention
```

2 - Create environment file

Create a .env file in the root directory (next to docker-compose.yml) with the following variables:
```properties
MYSQL_DATABASE=vivo_interventiondb
MYSQL_USER=mysql_user
MYSQL_PASSWORD=mysql_password
MYSQL_ROOT_PASSWORD=mysql_root_password
DB_URL=jdbc:mysql://vivo-db:3306/vivo_interventiondb
JWT_SECRET=your_jwt_secret_key_here
AZURE_CLIENT_ID=your_azure_client_id_here
AZURE_CLIENT_SECRET=your_azure_client_secret_here
AZURE_TENANT_ID=your_azure_tenant_id_here
VITE_API_BACKEND_SERVER=http://localhost:8000
VITE_AZURE_CLIENT_ID=your_azure_client_id_here
```

3 - build the jar files for the spring app
```bash
cd backend
./mvnw clean package -DskipTests
```

4 - build static files for the dist
``` bash
cd frontend/my-vivoIntervention-app
npm run build
```

5 - Build and start containers

```bash
docker-compose up -d --build
```
6 - Wait for containers to start

Check the status with:

```bash
docker-compose ps
```

All containers should show "Up" status.

7 - Access the application

Frontend: `http://localhost:80`

Backend API: `http://localhost:8000`

8 - Create initial Superuser
Once all containers are running, create the first superuser:

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

### 🐳 Docker Architecture
The system uses Docker Compose with three services:

* vivo-db: MySQL 8.0 database with persistent storage

* vivo-backend: Spring Boot application with JWT authentication

* vivo-frontend: React application with Vite build system

### 🔐 Environment Variables Explained
* Database variables: Configure MySQL connection and credentials

* JWT_SECRET: Secret key for signing authentication tokens

* Azure variables: For OneDrive integration and Power BI exports

* VITE_ variables: Frontend environment variables for API connection

### 🔐 Roles & Permissions

* Superuser → Full control (manage users, sites, intervention types, exports).

* Supervisor → Manage interventions and technicians, export data.

* Technician → Limited access (create/view own interventions).

### 🛠️ Tech Stack

* Frontend → React, Axios, Context API, MSAL.js

* Backend → Java, Spring Boot, Spring Security, Spring Data JPA, JWT

* Database → MySQL

* Cloud → Azure Entra ID (OneDrive integration)

* Tools → Git, GitHub, Docker Maven, npm

### 📊 Power BI Integration

* Exported CSV files are automatically uploaded to OneDrive, enabling seamless integration with Power BI for visualization and reporting.

### 📫 Contributing

* Contributions are welcome! Please open issues or pull requests.

