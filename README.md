# MediCare – Hospital Management System 🏥

MediCare is a full-stack Hospital Management System developed as part of my learning journey.

This repository contains the **backend application** built using Java and Spring Boot. It provides REST APIs for managing patients, doctors, appointments, consultations, prescriptions, and authentication.

## 🚀 Features

- 👤 Patient registration and profile management
- 👨‍⚕️ Doctor profiles and specializations
- 📅 Appointment booking and management
- 🩺 Doctor consultations and medical history
- 💊 Prescription management
- 🔐 JWT-based authentication
- 🛡️ Spring Security integration
- 👥 Role-based access for patients and doctors
- 🗄️ MySQL database integration
- 🌐 RESTful APIs

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- REST APIs

### Database
- MySQL

### Tools
- Maven
- Git
- GitHub
- Railway

## 🏗️ Project Structure

The backend follows a layered architecture:

- **Controller** – Handles REST API requests
- **Service** – Contains business logic
- **Repository** – Handles database operations
- **Entity/Model** – Represents database entities
- **Security** – Handles authentication and authorization
- **JWT** – Handles token generation and validation

## 🔐 Authentication

The application uses **Spring Security and JWT authentication**.

Users authenticate through the login system and receive a JWT token. The token is then used to access protected APIs.

The JWT secret is stored as an environment variable and is not hard-coded in the source code.

## 🗄️ Database

The application uses **MySQL** for storing application data.

Database configuration is provided through environment variables:

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
