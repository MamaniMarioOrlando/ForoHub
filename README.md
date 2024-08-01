## Descripción
La API de Foro Hub es una aplicación backend desarrollada para facilitar funcionalidades de 
foros de discusión. Construida con Java y Spring Boot, proporciona endpoints RESTful robustos para 
gestionar tópicos, mensajes, autenticación de usuarios y más. Esta API se integra perfectamente
con MySQL para el almacenamiento de datos y utiliza Swagger para una documentación clara y detallada de la API.

## Funcionalidades
* Gestión de Usuarios: Registro de nuevos usuarios y autenticación de usuarios existentes mediante 
tokens JWT (JSON Web Tokens).
* Gestión de Tópicos: Creación, actualización y cierre de tópicos de discusión.
* Gestión de Mensajes: Adición, eliminación y recuperación de mensajes dentro de los tópicos.
* Filtrado por Curso: Filtrado de tópicos basado en cursos asociados.
* Paginación: Uso de solicitudes paginables para una recuperación eficiente de datos.

* ## Tecnologías Utilizadas
* Java 17: Lenguaje de programación para lógica backend.
* Spring Boot 2.6.5: Marco de trabajo para construir y desplegar aplicaciones Java.
* Swagger 3.0: Herramienta de documentación y exploración de API.
* MySQL 8: Sistema de gestión de base de datos relacional para almacenamiento de datos.
* Insomnia 2024.1: Cliente API RESTful para probar endpoints.
