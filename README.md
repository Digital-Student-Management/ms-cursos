# 📚 ms-cursos — Cursos, Niveles y Salas

Microservicio encargado de la estructura académica: **cursos**, **niveles** educativos y **salas**.

> Parte del proyecto GED. Para ejecutar **todo el sistema**, ver el [README raíz](../README.md).

---

## ⚙️ Datos técnicos

| | |
|---|---|
| **Puerto** | `8082` |
| **Base de datos** | `ms_cursos_db` (MySQL, se crea automáticamente) |
| **Stack** | Spring Boot 4 · Java 21 · Spring Data JPA · RestTemplate · springdoc (Swagger) |

Un **curso** referencia un **nivel**, una **sala** (opcional) y un **docente jefe** (usuario de ms-usuarios).

---

## 📡 Endpoints principales

### Cursos — `/api/cursos`
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/cursos` | Lista todos los cursos |
| GET | `/api/cursos/{id}` | Curso por ID |
| GET | `/api/cursos/docente/{idDocente}` | Cursos de un profesor jefe |
| POST | `/api/cursos` | Crea un curso |
| PUT | `/api/cursos/{id}` | Actualiza un curso |
| DELETE | `/api/cursos/{id}` | Elimina un curso |

### Niveles — `/api/niveles`  ·  Salas — `/api/salas`
Ambos ofrecen el CRUD estándar (`GET`, `GET/{id}`, `POST`, `PUT/{id}`, `DELETE/{id}`).

---

## ▶️ Ejecución

```bash
mvnw.cmd spring-boot:run     # Windows
./mvnw spring-boot:run       # Linux / macOS
```

- Documentación Swagger: **http://localhost:8082/swagger-ui.html**

---

## 🔗 Relación con otros servicios

Consulta **ms-usuarios** (`:8089`) para validar el docente jefe de cada curso.
