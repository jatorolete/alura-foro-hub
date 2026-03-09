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
src/main/java/com/alura/forohub
├── controller
├── domain
│    ├── topico
│    ├── usuario
│    └── curso
└── AluraForoHubApplication.java



## 🛢️ Base de datos
- MySQL 8
- Migraciones gestionadas con Flyway
- Tablas: usuarios, cursos, tópicos, respuestas

## 🔐 Seguridad
Pendiente de implementación (JWT en próximas tarjetas del challenge).

## 🧪 Pruebas
Se realizarán una vez configurada la autenticación.