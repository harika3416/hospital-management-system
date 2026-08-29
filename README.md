# MediCare – Hospital Management System 🏥

MediCare is a full-stack Hospital Management System developed using **React, Java Spring Boot, MySQL, Spring Security, and JWT**.

The application provides separate functionality for patients and doctors, including authentication, profile management, appointments, consultations, medical history, and prescriptions.

> This repository contains the **Spring Boot backend** and REST APIs for the application.

## 🌐 Live Demo

[Open MediCare Hospital Management System](https://hospital-frontend-ashy-theta.vercel.app/)

## 🚀 Features

### 👤 Patient Module

- Patient registration and login
- Patient profile management
- View doctor information
- Book and manage appointments
- View consultation and medical history
- View prescriptions

### 👨‍⚕️ Doctor Module

- Doctor registration and login
- Doctor profile management
- Manage appointments
- Conduct patient consultations
- Maintain patient medical history
- Create and manage prescriptions

### 🔐 Authentication & Security

- JWT-based authentication
- Spring Security integration
- Role-based authorization
- Protected REST APIs
- Secure password handling
- JWT secret stored using environment variables

### 🗄️ Database

- MySQL database integration
- Persistent data storage using Spring Data JPA
- Hibernate ORM

## 🛠️ Tech Stack

### Frontend

- React
- JavaScript
- CSS

### Backend

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- REST APIs

### Database

- MySQL

### Deployment & Tools

- Maven
- Git
- GitHub
- Railway
- Vercel

## 🏗️ Backend Architecture

The backend follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
