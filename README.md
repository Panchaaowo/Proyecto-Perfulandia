#  Perfulandia Spa – Sistema de Gestión de Productos Cosméticos

**Perfulandia Spa** es una aplicación web orientada a la administración de productos cosméticos, stock, usuarios, pedidos y envíos. Está diseñada para gestionar eficientemente la operación de una tienda, permitiendo el seguimiento completo de cada elemento del flujo de trabajo.

---

## ✨ Características Principales

### 👥 Gestión de Usuarios
- Registro, edición, eliminación y consulta de usuarios.
- Control opcional de acceso basado en roles.

### 🧼 Gestión de Productos
- CRUD completo (crear, leer, actualizar y eliminar) de productos.
- Atributos como nombre, precio, descripción, stock y categoría.

### 📦 Gestión de Pedidos
- Creación y seguimiento de pedidos realizados por los usuarios.
- Asociación directa entre usuarios, productos y estado del pedido (pendiente, enviado, entregado, cancelado).

### 🚚 Gestión de Envíos
- Registro y seguimiento de envíos vinculados a pedidos.
- Actualización de estado y control de destino del envío.

### 🌐 API RESTful
- Endpoints REST tradicionales mediante Spring Web.
- Estructura de respuestas clara, ideal para integraciones frontend.

---

## 🧪 Pruebas, Desarrollo y Carga de Datos

- Se utilizó la dependencia **Datafaker** para generar datos ficticios de forma automática al inicio de la aplicación, a través de un componente `DataLoader`.
- Se crearon pruebas unitarias para **servicios y controladores** usando **JUnit 5** y **Mockito**.
- Se utilizó la opción **"Run with Coverage"** del IDE para verificar que las pruebas alcanzaran los requisitos mínimos solicitados.
- Se creó un archivo `application-test.properties` para ejecutar pruebas de integración sobre una base de datos aislada en modo `create-drop`.

#### `src/test/resources/application-test.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Perfulandia
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

- Incluye prueba del `DataLoader` para verificar la carga automática de entidades en la base de datos.

---

## 🚀 Tecnologías Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Web**
- **Spring Data JPA**
- **Lombok**
- **Maven**

### Base de Datos
- **MySQL 8+** y **Laragon**

### Desarrollo y Pruebas
- **JUnit 5** y **Mockito**
- **Datafaker**
- **Springdoc OpenAPI (Swagger UI)** para documentación interactiva
- **Postman** para pruebas manuales

---

## ⚙️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- JDK 17 o superior  
- Apache Maven  
- MySQL Server 8.x  
- IDE compatible (IntelliJ IDEA, Eclipse o VS Code)


https://github.com/Panchaaowo/Proyecto-Perfulandia.git
cd Proyecto-Perfulandia
