# Proyecto Perfulandia

Este proyecto es una aplicación de gestión para **Perfulandia**, que permite a los usuarios gestionar envíos, pedidos, productos y usuarios de manera eficiente. La aplicación está construida utilizando **Spring Boot** y **Lombok** para simplificar el desarrollo.

## Características

Gestión de Usuarios
Registro, edición, eliminación y consulta de usuarios.
Control del acceso a funcionalidades según el rol del usuario (opcional).

Gestión de Productos
CRUD completo (crear, leer, actualizar y eliminar) de productos.
Manejo de stock y atributos como nombre, precio, descripción y categoría.

Gestión de Pedidos
Creación y seguimiento de pedidos realizados por los usuarios.
Asociación directa entre usuarios, productos y estados de pedido (pendiente, enviado, entregado, cancelado).

Gestión de Envíos
Registro y seguimiento de envíos asociados a pedidos.
Actualización del estado del envío y control de destino.

## Tecnologías Utilizadas

Backend:
-Java 17: Lenguaje de programación principal.
-Spring Boot 3.4.5: Framework para desarrollo de aplicaciones empresariales.
-Spring Web: Para la exposición de servicios RESTful.
-Spring Data JPA: Para la persistencia de datos y consultas a la base de datos.
-Lombok: Para reducir el código repetitivo (getters, setters, constructores).
-Maven: Sistema de construcción y gestión de dependencias.

Base de Datos:
-MySQL 8+: Sistema de gestión de base de datos relacional.

Desarrollo y Pruebas:
-JUnit 5 y Mockito: Para pruebas unitarias con alta cobertura.
-Datafaker: Generación de datos simulados para testing.
-JaCoCo: Medición de cobertura de código con umbral mínimo del 80%.
-Swagger / OpenAPI 3: Documentación interactiva de los endpoints.
-Postman: Para pruebas manuales de las APIs REST.

## Requisitos Previos

- **JDK 17 o superior.**
- **Maven** para la gestión de dependencias.
- Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:
- MySQL Server 8.x

## Instalación

### Clonar el Repositorio:

```bash
git clone https://github.com/Panchaaowo/Proyecto-Perfulandia
cd Proyecto-Perfulandia
