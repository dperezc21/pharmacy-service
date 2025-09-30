# Inventory Management Backend Service

This is a backend service built with **Spring Boot**, following the principles of **Hexagonal Architecture**, and secured with **Spring Security** using token-based authentication. The application provides a robust API for managing users and product inventory, with support for role-based access control and multiple pricing types.

## Features

- 🔐 **Token-Based Authentication** with Spring Security
- 🧾 **User CRUD Operations**
- 📦 **Inventory Management**:
    - Track product stock
    - Handle different price types for buying and selling
- 🗃️ **Data Persistence** with MySQL
- 🧱 **Hexagonal Architecture** (Ports and Adapters)

## Technologies Used

- Java 17+
- Spring Boot
- Spring Security (JWT token-based authentication)
- Spring Data JPA
- MySQL
- Gradle
- Hexagonal Architecture (Domain-driven design)

## API Overview

### Authentication

- `POST /auth/login` — Authenticate a user and receive a token
- `POST /auth/register` — Register a new user

### Users (Requires ADMIN role)

- `GET /users` — List all users
- `POST /users` — Create a new user
- `GET /users/{id}` — Get user by ID
- `PUT /users/{id}` — Update user
- `DELETE /users/{id}` — Delete user

### Products

- `GET /products` — List all products
- `POST /products` — Create a new product
- `GET /products/{id}` — Get product by ID
- `PUT /products/{id}` — Update product
- `DELETE /products/{id}` — Delete product
- `GET /products/inventory` — Get products inventory to display stock