# Alura Foro Hub – API REST con Spring Boot 3
Proyecto desarrollado como parte del Challenge de Alura Latam.
La API permite gestionar tópicos de un foro: creación, listado, detalle, actualización y eliminación.

## 🚀 Tecnologías utilizadas
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Flyway
- Lombok (si lo usas)
- DevTools

## 📚 Funcionalidades implementadas
✔️ 1. Registrar un tópico (POST /topicos)
- Validación de duplicados
- Validación de autor y curso
- Persistencia en base de datos
  ✔️ 2. Listar tópicos (GET /topicos)
- Ordenamiento por fecha
- Filtro por curso
- Filtro por año
- Paginación opcional
  ✔️ 3. Detalle de un tópico (GET /topicos/{id})
  ✔️ 4. Actualizar un tópico (PUT /topicos/{id})
  ✔️ 5. Eliminar un tópico (DELETE /topicos/{id})

## 🗂️ Estructura del proyecto
src/main/java/com/alura/forohub ├── controller ├── domain │    ├── topico │    ├── respuesta │    └── usuario ├── infra │    └── security └── AluraForoHubApplication.java

---

## 🛢️ Base de datos

La base de datos se gestiona con **Flyway**.  
La migración inicial crea las tablas:

- `usuarios`
- `topicos`
- `respuestas`

Cada respuesta está asociada a:
- un **usuario** (autor)
- un **tópico**

---

## 🔑 Seguridad (JWT)

El proyecto implementa:
- Filtro personalizado para validar tokens
- Servicio para generar y verificar JWT
- Autenticación basada en email + contraseña
- Protección de rutas privadas

---

## 📬 Endpoints principales

### 🔐 Autenticación

POST /login

### 🧵 Tópicos


POST /topicos GET /topicos GET /topicos/{id} PUT /topicos/{id} DELETE /topicos/{id}

### 💬 Respuestas


POST /respuestas GET /respuestas GET /respuestas/{id} PUT /respuestas/{id} DELETE /respuestas/{id}

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio:


git clone https://github.com/jatorolete/alura-foro-hub.git (github.com in Bing)

2. Configurar variables de entorno:


DB_URL=jdbc:mysql://localhost:3306/forohub DB_USER=root DB_PASSWORD=tu_password JWT_SECRET=tu_secreto

3. Ejecutar la aplicación:


mvn spring-boot:run

---

## 🧪 Pruebas con Postman

Incluye:
- Token JWT en el header:  
  `Authorization: Bearer <token>`
- Cuerpo JSON para crear y actualizar recursos

---

## 📄 Licencia

Este proyecto fue desarrollado como parte del programa **Oracle Next Education + Alura Latam**.

---

## 👤 Autor

**Jorge (Korzo)**  
Desarrollador Backend | Java & Spring Boot
