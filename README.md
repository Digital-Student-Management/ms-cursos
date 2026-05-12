# ms-cursos 📚

Microservicio encargado de la gestión de cursos del Sistema Integral de Gestión Estudiantil Digital del **Colegio Bernardo O'Higgins**.

---

## 🧱 Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 4.x |
| MySQL | 8.x |
| Lombok | Latest |
| SpringDoc (Swagger) | Latest |

---

## ⚙️ Configuración

### Requisitos previos
- Java 21 instalado (JDK)
- MySQL corriendo en `localhost:3306`
- Base de datos: se crea automáticamente al levantar el proyecto

### Variables en `application.properties`

```properties
spring.application.name=ms-cursos
server.port=8082

spring.datasource.url=jdbc:mysql://localhost:3306/ms_cursos_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

springdoc.swagger-ui.path=/swagger-ui.html
