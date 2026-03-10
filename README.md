# Foro Hub - API REST de Discusión 

**Foro Hub** es una API REST robusta construida con **Java** y **Spring Boot**. Este proyecto fue desarrollado como parte del Challenge Back-End del programa **ONE - Oracle Next Education (Alura Latam)**, con el objetivo de simular el núcleo funcional de un foro educativo.

El sistema permite gestionar tópicos de discusión mediante operaciones CRUD, integrando persistencia de datos relacional y estándares de seguridad avanzados para el manejo de usuarios.

---

## Tecnologías Utilizadas

| Categoría | Tecnologías |
| :--- | :--- |
| **Lenguaje** | Java 17 |
| **Framework** | Spring Boot 3 |
| **Seguridad** | Spring Security & JWT (JSON Web Tokens) |
| **Persistencia** | JPA / Hibernate |
| **Bases de Datos** | MySQL (Producción) / H2 (Pruebas) |
| **Herramientas** | Maven, Flyway (Migraciones), Lombok |
| **Validación** | Bean Validation (Jakarta) |

---

## Arquitectura de Seguridad

La aplicación implementa un esquema de **autenticación y autorización apátrida (stateless)**:

* **Endpoints Públicos:** Registro de usuarios y autenticación (`/login`).
* **Endpoints Protegidos:** Requieren un Bearer Token válido en el encabezado `Authorization`.
* **Validación de Datos:** Uso de anotaciones para garantizar que los tópicos no contengan campos nulos o duplicados antes de la persistencia.



---

## Funcionalidades Implementadas

La API ofrece un control completo sobre los recursos de discusión:

* [x] **Gestión de Tópicos:** Crear, listar, consultar por ID, actualizar y eliminar tópicos.
* [x] **Autenticación:** Generación y validación de tokens JWT.
* [x] **Migraciones:** Control de versiones de la base de datos con Flyway.
* [x] **Paginación:** Las listas de tópicos están optimizadas mediante objetos `Pageable`.

### Endpoints Disponibles

| Método | Endpoint | Acción |
| :--- | :--- | :--- |
| **POST** | `/topicos` | Crear un nuevo tópico. |
| **GET** | `/topicos` | Listar todos los tópicos (Paginado). |
| **GET** | `/topicos/{id}` | Ver detalle de un tópico específico. |
| **PUT** | `/topicos/{id}` | Actualizar contenido de un tópico. |
| **DELETE** | `/topicos/{id}` | Eliminar un tópico. |

---

## Cómo Ejecutar el Proyecto

### Prerrequisitos
* **JDK 17** o superior.
* **Maven 3.8+**.
* Instancia de **MySQL** (opcional, configurado por defecto para compatibilidad con H2).

### Comandos de Instalación
1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/aquilescb/foro_hub.git](https://github.com/aquilescb/foro_hub.git)
   cd foro_hub
Configurar Propiedades:
Utilice el archivo application.properties.example como referencia para configurar su clave secreta JWT y credenciales de base de datos en src/main/resources/application.properties.

## Ejecutar la Aplicación:

Bash
./mvnw spring-boot:run
Notas Adicionales
Migraciones Flyway: Las tablas se crean y actualizan automáticamente al iniciar la aplicación basándose en los scripts ubicados en src/main/resources/db/migration.

## Pruebas: 
Se recomienda el uso de Postman o Insomnia. Asegúrese de copiar el token recibido en el login y agregarlo como Bearer Token en las solicitudes protegidas.

## Validaciones: 
El sistema impide la creación de tópicos con el mismo título y mensaje para evitar redundancia.