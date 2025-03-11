# Flight Booking System

A robust monolithic application for managing flight bookings built with Spring Boot.

## 🎓 About
Final project developed for the "Java Full Stack Developer" course at SkillFactory(NA). This project demonstrates the practical application of enterprise-level Java development skills and best practices learned throughout the course.

## 🛠 Tech Stack

- **Spring Boot** - Core framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM
- **MySQL** - Database
- **Spring Security** - Authentication & Authorization
- **Thymeleaf** - Server-side templating
- **Bootstrap** - Frontend styling

## ✨ Features

- **User Authentication**: Secure login and registration system
- **Flight Booking Management**: 
  - Create new bookings
  - View existing bookings
  - Update booking details
  - Delete bookings
- **Validation System**:
  - Flight code validation (2 uppercase letters + 4 digits)
  - Price validation
  - Passenger count validation
  - Aircraft selection validation

## 🏗 Architecture

Monolithic architecture with clear separation of concerns:
- Controllers for handling HTTP requests
- Services for business logic
- Repositories for data access
- Models for data representation
- Views for user interface

## 💾 Database Schema

- Users table for authentication
- Bookings table for flight reservations
- Relationships managed through JPA annotations

## 🚀 Getting Started

1. Clone the repository
2. Configure MySQL database
3. Update application.properties with your database credentials
4. Run the application using Maven
5. Access the application at `http://localhost:8098`

### Initial Setup
The application includes an automatic initialization service that runs on first startup:
- Creates default roles (ADMIN and USER)
- Sets up a superadmin account with both roles
- Default credentials:
  - Username: superadmin
  - Password: superadmin123

## 🔑 Role System
- **ADMIN**: Full system access and management capabilities
- **USER**: Standard booking functionalities
- Role-based access control through Spring Security

![Image](https://github.com/user-attachments/assets/ca09cb27-ff67-409d-b981-0e3796d7a9bb)

![Image](https://github.com/user-attachments/assets/b12f98d7-76a0-4717-bd6a-d7ecfe77b5d8)

![Image](https://github.com/user-attachments/assets/f18321d0-4446-4980-996f-aee1314aaaf5)

![Image](https://github.com/user-attachments/assets/cad10c66-e420-47f1-adba-64e50b8608f3)
