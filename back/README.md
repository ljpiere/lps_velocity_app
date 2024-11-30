
# Explicación del Código de la Aplicación Velocity

Este archivo `README.md` está diseñado para proporcionar una visión detallada del código que compone la aplicación **Velocity**. Velocity es una aplicación de gestión de usuarios y bicicletas desarrollada con **Spring Boot** y **Angular**, y cuenta con varias características adicionales que se habilitan o deshabilitan según las configuraciones del producto.

## Tabla de Contenidos
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Backend - Arquitectura y Componentes](#backend---arquitectura-y-componentes)
  - [Controladores](#controladores)
  - [Servicios](#servicios)
  - [Repositorios](#repositorios)
  - [Entidades](#entidades)
- [Frontend - Plantillas HTML](#frontend---plantillas-html)
- [Configuración del Producto](#configuración-del-producto)
- [Flujo de la Aplicación](#flujo-de-la-aplicación)
- [Implementación de Características Adicionales](#implementación-de-características-adicionales)

## Estructura del Proyecto

El proyecto Velocity está estructurado de la siguiente manera:

- **Backend**: Contiene el código Java que define la lógica del negocio, acceso a datos, controladores y servicios. Este módulo utiliza Spring Boot.
- **Frontend**: Se compone de plantillas HTML que se encuentran en `src/main/resources/templates`, que se sirven a través del backend de Spring Boot.
- **Configuración**: El archivo `application.properties` permite controlar qué características están habilitadas o deshabilitadas en la aplicación.

## Backend - Arquitectura y Componentes

La parte de backend de Velocity sigue una arquitectura típica de **MVC (Modelo-Vista-Controlador)**. A continuación se explica cada uno de los componentes:

### Controladores

Los controladores están ubicados en `src/main/java/com/velocity/back/controller`. Los controladores son responsables de recibir solicitudes HTTP, interactuar con los servicios para manejar la lógica de negocio y devolver las respuestas apropiadas a los usuarios.

Ejemplo de controlador: `BackController.java`, que gestiona las rutas para registro de bicicletas, gestión de clubes, creación y edición de eventos, etc.

- **Controlador Principal** (`BackController`): 
  - Define rutas para manejar el registro de usuarios y bicicletas.
  - Gestiona la interacción entre la vista (HTML) y la lógica de negocio (servicios).
  - Realiza validaciones para la autenticación del usuario.

### Servicios

Los servicios encapsulan la lógica de negocio y son utilizados por los controladores para realizar las acciones necesarias. Se encuentran en `src/main/java/com/velocity/back/service`.

Por ejemplo:
- **CreacionEventos**: Implementa la lógica para la creación de eventos.
- **GestionAlquiler**: Contiene la lógica para alquilar bicicletas.

Los servicios interactúan con los repositorios para realizar consultas o modificaciones a la base de datos.

### Repositorios

Los repositorios son interfaces que se encargan del acceso a datos. Extienden de `JpaRepository` para proporcionar métodos CRUD automáticos. Se encuentran en `src/main/java/com/velocity/back`.

Ejemplos de repositorios:
- **UserRepository**: Administra el acceso a la tabla `AppUser` en la base de datos.
- **BikeRepository**: Gestiona la persistencia de bicicletas.

Los repositorios son la capa encargada de las operaciones de lectura/escritura sobre la base de datos.

### Entidades

Las entidades representan las tablas en la base de datos y están ubicadas en `src/main/java/com/velocity/back/entity`. Cada clase de entidad está anotada con `@Entity` y contiene los atributos que se traducen a columnas en la base de datos.

Ejemplos:
- **AppUser**: Representa un usuario registrado, con atributos como `id`, `name`, `username`, y `password`.
- **Bike**: Representa una bicicleta, con atributos como `id`, `name`, `brand`, y `owner`.

Cada entidad tiene relaciones (`@OneToMany`, `@ManyToOne`, etc.) que describen cómo están conectadas entre sí.

## Frontend - Plantillas HTML

Los archivos HTML que sirven como vistas se encuentran en `src/main/resources/templates`. La estructura de carpetas incluye:

- **auth**: Formularios de inicio de sesión (`login.html`) y registro (`register.html`).
- **bike**: Vistas para la gestión de bicicletas (`registerBike.html`, `myBikes.html`).
- **eventos**: Formularios para crear y editar eventos (`crearEvento.html`, `editarEvento.html`).
- **social**: Página para la simulación de una red social (`red_social.html`).
- **estadisticas**: Vistas para mostrar estadísticas de uso.
- **reportes**: Reportes ficticios para uso del gobierno.

Estas plantillas HTML utilizan **Thymeleaf** como motor de plantillas para inyectar dinámicamente datos del backend en la vista.

## Configuración del Producto

El archivo `application.properties` define qué características están habilitadas para la instancia actual del producto. Esta configuración se utiliza para definir una **línea de productos de software** y generar `.jar` específicos según los requisitos de los clientes.

Ejemplo:
```properties
features.registroCiclistas.enabled=true
features.redSocial.enabled=true
features.creacionEventos.enabled=true
```

Cada característica puede ser activada (`true`) o desactivada (`false`), lo que controla qué partes de la aplicación estarán accesibles para el usuario.

## Flujo de la Aplicación

1. **Registro e Inicio de Sesión**:
   - El usuario puede registrarse (`/register`) y luego iniciar sesión (`/login`).
   - Una vez autenticado, se redirige al usuario a la página principal donde puede ver todas las opciones disponibles según las características habilitadas.

2. **Gestión de Bicicletas**:
   - Los usuarios pueden registrar una bicicleta (`/registerBike`).
   - Las bicicletas registradas se pueden visualizar en `/myBikes`.

3. **Eventos**:
   - Los usuarios pueden crear eventos deportivos (`/crearEvento`).
   - Los eventos se pueden editar posteriormente (`/editarEvento`).

4. **Estadísticas y Reportes**:
   - **Estadísticas de Uso**: Los usuarios pueden ver estadísticas relacionadas con el uso de sus bicicletas.
   - **Reportes para el Gobierno**: Si la característica está habilitada, se pueden generar reportes ficticios que muestran detalles de uso de bicicletas, usuarios y eventos.

5. **Red Social Simulada**:
   - Los usuarios pueden interactuar con una red social ficticia para ver y crear publicaciones.

## Implementación de Características Adicionales

### Red Social (`moduloSocial`)
- **Clase**: `RedSocial`.
- **Función**: Simula una red social donde los usuarios pueden crear y ver publicaciones.
- **Vista HTML**: `red_social.html` en `src/main/resources/templates/social`.

### Estadísticas de Uso (`moduloEstadistica`)
- **Clase**: `EstadisUso`.
- **Función**: Muestra estadísticas ficticias de uso de la bicicleta, incluyendo distancia recorrida y tiempo de uso.
- **Vista HTML**: `estadisticasUso.html`.

### Reportes para el Gobierno (`moduloEstadistica`)
- **Clase**: `ReportesGobierno`.
- **Función**: Genera reportes de uso de bicicletas, participación en eventos, y estadísticas por usuario. Diseñado para ser utilizado por organismos gubernamentales.
- **Vista HTML**: `reportesGobierno.html`.


