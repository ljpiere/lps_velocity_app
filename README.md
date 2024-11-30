
# Velocity Full Stack Project

Este es el proyecto **Velocity**, una aplicación completa desarrollada con **Spring Boot** y **Angular** que permite la gestión de bicicletas, usuarios, eventos y características adicionales. Velocity está diseñado para ser flexible y escalable, lo que facilita su uso en diferentes tipos de escenarios y clientes, incluyendo productos dirigidos al gobierno para análisis y reportes.

## Tabla de Contenidos
- [Descripción del Proyecto](#descripción-del-proyecto)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Configuración del Archivo `application.properties`](#configuración-del-archivo-applicationproperties)
- [Requisitos Previos](#requisitos-previos)
- [Despliegue Local](#despliegue-local)
- [Despliegue en Azure](#despliegue-en-azure)
  - [Preparación de la Imagen Docker](#preparación-de-la-imagen-docker)
  - [Crear una Máquina Virtual en Azure](#crear-una-máquina-virtual-en-azure)
  - [Configurar la Base de Datos PostgreSQL en Azure](#configurar-la-base-de-datos-postgresql-en-azure)
- [Cómo Realizar Cambios en la Aplicación](#cómo-realizar-cambios-en-la-aplicación)
  - [Cambios en el Backend](#cambios-en-el-backend)
  - [Cambios en el Frontend](#cambios-en-el-frontend)
- [Cómo Contribuir](#cómo-contribuir)
- [Licencia](#licencia)
- [Contacto](#contacto)

## Descripción del Proyecto

Velocity es una aplicación web diseñada para gestionar las actividades relacionadas con bicicletas, usuarios, eventos y características adicionales. Está pensada para diferentes escenarios de uso:
- **Usuarios Individuales**: Registro de bicicletas y gestión de clubes.
- **Gobierno**: Reportes detallados sobre el uso de bicicletas y la participación de los usuarios en eventos.
- **Empresas**: Características adicionales como servicios terceros.

### Características Clave
- **Registro de Usuarios y Bicicletas**.
- **Alquiler de Bicicletas**.
- **Gestión de Clubes y Eventos**.
- **Red Social Simulada** para la interacción entre usuarios.
- **Reportes para el Gobierno**.

## Estructura del Proyecto

El proyecto está organizado en los siguientes módulos:

### Backend (`src/main/java/com/velocity/back`)
En la carpeta `back` encontrarás unn README.md con la explicación del código y cómo está construida la aplicación:

- **moduloRegistro**: Gestiona el registro de ciclistas, clubes y alquiler de bicicletas.
- **moduloBicicleta**: Maneja la administración de bicicletas y alquileres.
- **moduloEstadistica**: Módulo para estadísticas de uso y generación de reportes.
- **moduloEventos**: Permite la creación y edición de eventos deportivos.
- **moduloSocial**: Incluye funcionalidades simuladas de red social.

### Frontend (`src/main/resources/templates`)
- **auth**: Formularios de login y registro de usuarios.
- **bike**: Registro y gestión de bicicletas.
- **eventos**: Crear, editar y visualizar eventos.
- **estadisticas**: Vistas para mostrar estadísticas de uso.
- **reportes**: Generación de reportes ficticios para el gobierno.
- **social**: Simulación de red social para usuarios.

### Configuración
- **`application.properties`**: Configura la base de datos, el servidor, y habilita o deshabilita características.
- **Dockerfile**: Contiene las instrucciones para empaquetar la aplicación en un contenedor Docker.

## Configuración del Archivo `application.properties`

El archivo `application.properties` permite configurar la aplicación y habilitar/deshabilitar diferentes características.

Ejemplo de configuración:

```properties
spring.datasource.url=jdbc:postgresql://<db-host>:5432/arq
spring.datasource.username=arq
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

features.registroCiclistas.enabled=true
features.redSocial.enabled=true
features.estadisUso.enabled=true
features.registroBicicleta.enabled=true
features.serviciosTerceros.enabled=true
features.gestionClubes.enabled=true
features.gestionAlquiler.enabled=true
features.creacionEventos.enabled=true
features.edicionEventos.enabled=true
features.reportesGobierno.enabled=true
```

Cada propiedad `features.<nombre>.enabled` controla si una característica está habilitada o no en la aplicación.

## Requisitos Previos

Antes de iniciar el despliegue, asegúrate de tener instalados los siguientes componentes:

- **Java 19**.
- **Maven** para la compilación del backend.
- **Docker** para empaquetar la aplicación.
- **Azure Account** para el despliegue en la nube.

## Despliegue Local

Para desplegar la aplicación localmente:

1. **Clonar el Repositorio**:

   ```sh
   git clone https://github.com/tuusuario/velocity.git
   ```
2. Ejecuta un contenedor de Docker utilizando el archivo de configuracion en la carpeta dockerfile_postgresql disponible en el repositorio.

3. Revisa el archivo `aplication.properties` para validar los atributos de configuración de tu imagen de Docker. Revisa las features disponibles.

4. **Compilar y Ejecutar la Aplicación**: En una terminal, sobre la carpeta `back` donde se encuentra el archivo `pom.xml` ejecuta la aplicación:

   ```sh
   mvn clean package
   mvn spring-boot:run
   ```

O puedes probar con: 

   ```sh
   mvn clean package
   mvn spring-boot:run
   ```
5. **Acceder a la Aplicación**:

   Abre tu navegador en `http://localhost:8080`.

## Despliegue en Azure

### Preparación de la Imagen Docker
La imagen de docker está disponible en: https://hub.docker.com/repository/docker/jeanpierec/velocity_full/general

1. **Construir la Imagen**:

   ```sh
   docker build -t jeanpierec/velocity_full:latest .
   ```

2. **Subir la Imagen a Docker Hub**:

   ```sh
   docker push jeanpierec/velocity_full:latest
   ```

### Crear una Máquina Virtual en Azure

1. **Crear la Máquina Virtual**:

   - Inicia sesión en el [portal de Azure](https://portal.azure.com/).
   - Crea una máquina virtual Ubuntu y abre el puerto `8080` para acceso público.

2. **Instalar Docker**:

   Conéctate a la máquina y ejecuta:

   ```sh
   sudo apt update
   sudo apt install docker.io -y
   sudo systemctl start docker
   sudo systemctl enable docker
   ```

3. **Ejecutar la Imagen Docker**:

   ```sh
   docker run -d -p 8080:8080 jeanpierec/velocity_full:latest
   ```

4. **Abrir el Puerto 8080**:

   Asegúrate de tener configurada una regla de seguridad que permita el tráfico en el puerto `8080`.

### Configurar la Base de Datos PostgreSQL en Azure

- **Azure Database for PostgreSQL** se utilizó para la base de datos.
- La configuración se realizó en `application.properties` apuntando a la instancia de Azure.

```properties
spring.datasource.url=jdbc:postgresql://<azure-db-host>:5432/arq
spring.datasource.username=arq
spring.datasource.password=password
```

## Cómo Realizar Cambios en la Aplicación

### Cambios en el Backend

1. **Modificar o Crear Nuevas Clases**:

   Las clases de negocio se encuentran en `src/main/java/com/velocity/back`. Puedes agregar nuevos controladores, servicios, o repositorios según sea necesario.

2. **Compilar y Verificar**:

   Después de realizar cambios, compila la aplicación:

   ```sh
   mvn clean package
   ```

### Cambios en el Frontend

1. **Modificar Archivos HTML**:

   Los archivos HTML se encuentran en `src/main/resources/templates`. Puedes editarlos para ajustar la interfaz de usuario.

2. **Pruebas Locales**:

   Puedes probar la aplicación localmente ejecutándola con:

   ```sh
   mvn spring-boot:run
   ```

## Cómo Contribuir

1. **Clonar el Repositorio**:

   ```sh
   git clone https://github.com/tuusuario/velocity.git
   ```

2. **Crear una Nueva Rama**:

   ```sh
   git checkout -b feature/nueva-feature
   ```

3. **Hacer Commit de los Cambios**:

   ```sh
   git add .
   git commit -m "Añadir nueva característica [nombre]"
   ```

4. **Enviar los Cambios al Repositorio**:

   ```sh
   git push origin feature/nueva-feature
   ```

5. **Crear un Pull Request** en GitHub para revisión.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Puedes consultar más detalles en el archivo `LICENSE`.
