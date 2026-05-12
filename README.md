# 📚 Microservicio de Cursos (ms-cursos)

Este repositorio contiene el código fuente del **Microservicio de Cursos**, parte integral de la arquitectura de microservicios híbrida del **Sistema Integral de Gestión Estudiantil Digital**.

Este módulo actúa como el núcleo central para la organización académica del colegio, gestionando los cursos y su composición anual.

---

## 📝 Descripción del Módulo

El **ms-cursos** es responsable de administrar de forma centralizada los cursos del sistema escolar. Centraliza la lógica de negocio para la creación, lectura, actualización y eliminación (CRUD) de cursos, evitando la redundancia de datos en otros microservicios y sirviendo como referencia para módulos como evaluaciones, asistencia y anotaciones.

---


## 🛠️ Stack Tecnológico

El proyecto está construido bajo los estándares más modernos de la industria:

| Componente | Tecnología |
|---|---|
| Lenguaje | Java 21 |
| Framework | Spring Boot 4.x |
| Gestor de dependencias | Maven |
| Base de Datos | MySQL (Local) |
| ORM | Spring Data JPA / Hibernate |
| Validaciones | Spring Boot Validation |
| Comunicación | REST (RestTemplate) |
| Herramientas extra | Lombok, SpringDoc (Swagger) |

---

## 🚀 Instalación y Ejecución en Entorno Local

### 1. Clonar el repositorio

```bash
git clone https://github.com/Digital-Student-Management/ms-cursos.git
cd ms-cursos
