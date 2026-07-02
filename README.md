# 🛒 TechLab E-Commerce Backend

Backend desarrollado como **Proyecto Final** del curso **Java Backend** utilizando **Spring Boot** y **MySQL**.

La aplicación implementa una API REST para la gestión de un sistema de e-commerce, permitiendo administrar productos, categorías, usuarios y pedidos, siguiendo una arquitectura en capas y buenas prácticas de desarrollo.

---

# 📚 Tecnologías utilizadas

- Java 26
- Spring Boot 3.5.16
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL 8
- Maven
- Swagger OpenAPI
- Visual Studio Code

---

# 📁 Arquitectura del proyecto

El proyecto está organizado utilizando una arquitectura en capas:

```
src/main/java/com/techlab/ecommerce

├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── service
│   └── impl
└── TechlabBackendApplication.java
```

### Descripción

- **config** → Configuraciones generales (Swagger, inicialización de datos, etc.)
- **controller** → Endpoints REST
- **dto** → Objetos de transferencia de datos
- **entity** → Entidades JPA
- **enums** → Enumeraciones utilizadas por la aplicación
- **exception** → Excepciones personalizadas
- **repository** → Acceso a la base de datos
- **service** → Interfaces de negocio
- **service/impl** → Implementación de la lógica de negocio

---

# 🚀 Funcionalidades

## Categorías

- Crear categoría
- Obtener categorías
- Buscar categoría
- Actualizar categoría
- Eliminar categoría

---

## Productos

- Crear producto
- Obtener productos
- Buscar producto
- Actualizar producto
- Eliminar producto
- Asociación con categorías
- Control de stock

---

## Usuarios

- Crear usuario
- Obtener usuarios
- Buscar usuario
- Actualizar usuario
- Eliminar usuario

---

## Pedidos

- Crear pedido
- Consultar pedidos
- Consultar pedidos por usuario
- Cancelar pedido
- Cálculo automático del total
- Validación de stock
- Descuento automático de stock

---

# 🗄 Base de datos

El proyecto utiliza **MySQL**.

Nombre de la base de datos:

```
techlab_db
```

Spring Boot crea automáticamente las tablas necesarias mediante Hibernate.

---

# ⚙ Configuración

Editar el archivo:

```
src/main/resources/application.properties
```

Configurar los datos de conexión a MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/techlab_db
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶ Cómo ejecutar el proyecto

## 1. Clonar el repositorio

```bash
git clone https://github.com/USUARIO/techlab-backend.git
```

---

## 2. Entrar al proyecto

```bash
cd techlab-backend
```

---

## 3. Crear la base de datos

Desde MySQL ejecutar:

```sql
CREATE DATABASE techlab_db;
```

---

## 4. Configurar las credenciales

Editar el archivo:

```
application.properties
```

---

## 5. Ejecutar la aplicación

Con Maven:

```bash
mvn spring-boot:run
```

O desde Visual Studio Code ejecutando:

```
TechlabBackendApplication.java
```

---

# 📖 Documentación de la API

Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

Desde Swagger es posible:

- visualizar todos los endpoints
- probar cada endpoint
- enviar peticiones HTTP
- visualizar respuestas JSON

---

# 🌐 Endpoints principales

## Productos

```
GET     /api/products
GET     /api/products/{id}
POST    /api/products
PUT     /api/products/{id}
DELETE  /api/products/{id}
```

---

## Categorías

```
GET     /api/categories
GET     /api/categories/{id}
POST    /api/categories
PUT     /api/categories/{id}
DELETE  /api/categories/{id}
```

---

## Usuarios

```
GET     /api/users
GET     /api/users/{id}
POST    /api/users
PUT     /api/users/{id}
DELETE  /api/users/{id}
```

---

## Pedidos

```
GET     /api/orders
GET     /api/orders/{id}
GET     /api/orders/user/{userId}
POST    /api/orders
PUT     /api/orders/{id}/cancel
DELETE  /api/orders/{id}
```

---

# 📦 Modelo de datos

La aplicación implementa las siguientes entidades:

- Category
- Product
- User
- Order
- OrderItem

Relaciones principales:

```
Category
      │
      │ 1
      │
      ▼
Product
      │
      │ *
      ▼
OrderItem
      ▲
      │ *
      │
Order
      ▲
      │ *
      │
User
```

---

# 📌 Características implementadas

- Arquitectura REST
- CRUD completo
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- DTOs
- Validaciones
- Manejo de excepciones
- Swagger
- Separación por capas
- Arquitectura escalable

---

# 📄 Autor

**Fernando Blanco**

Proyecto desarrollado como trabajo práctico final del curso **Java Backend**.