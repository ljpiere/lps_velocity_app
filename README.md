# Proyecto de Línea de Productos de Software en Spring Boot

Este proyecto implementa una línea de productos de software utilizando Spring Boot. La idea es generar diferentes combinaciones de características (“features”) basadas en configuraciones definidas en el archivo `application.properties`. Cada combinación define un producto diferente con su propia funcionalidad. A continuación, se detalla cómo se estructura el proyecto, las características implementadas, y cómo utilizar las funcionalidades actuales.

## Características del Proyecto

El proyecto tiene las siguientes características (“features”) implementadas:

1. **Registro de Ciclistas**: Permite registrar ciclistas y sus bicicletas.
2. **Registro de Bicicletas**: Permite registrar una bicicleta asociada a un usuario.
3. **Servicios de Terceros**: Permite mostrar servicios de terceros.
4. **Gestión de Clubes**: Permite que los usuarios se inscriban en un club.
5. **Gestión de Alquiler de Bicicletas**: Permite que los usuarios alquilen una bicicleta.

Cada característica se habilita o deshabilita desde el archivo de configuración `application.properties`. Esto permite generar diferentes versiones del producto habilitando o deshabilitando las características según sea necesario.

## Configuración en `application.properties`

En el archivo `application.properties`, se definen las propiedades que habilitan o deshabilitan las diferentes características. Un ejemplo de configuración es:

```properties
features.registroCiclistas.enabled=true
features.redSocial.enabled=true
features.estadisUso.enabled=false
features.registroBicicleta.enabled=true
features.serviciosTerceros.enabled=true
features.gestionClubes.enabled=true
features.gestionAlquiler.enabled=true
```

Si una característica está establecida en `true`, se habilitará y estará disponible en la aplicación. De lo contrario, estará deshabilitada.

## Arquitectura del Proyecto

El proyecto se estructura utilizando las anotaciones de Spring para cargar diferentes beans según la configuración habilitada.

- **@ConditionalOnProperty**: Esta anotación permite habilitar o deshabilitar beans según el valor de una propiedad en el archivo de configuración. Cada clase de servicio tiene esta anotación para que solo se registre como bean si la propiedad correspondiente está habilitada.

### Paquetes Principales

- `com.velocity.back.features.moduloRegistro`:
  - **RegistroCiclistas**: Registra ciclistas y bicicletas asociadas.
  - **RegistroBicicleta**: Registra bicicletas individualmente.
  - **GestionClubes**: Permite la inscripción de usuarios en clubes.
  - **GestionAlquiler**: Permite a los usuarios alquilar bicicletas.
- `com.velocity.back.club`:
  - **Club**: Entidad que representa un club.
  - **ClubRepository**: Repositorio para realizar operaciones CRUD sobre los clubes.
- `com.velocity.back.alquiler`:
  - **Alquiler**: Entidad que representa un registro de alquiler de bicicleta.
  - **AlquilerRepository**: Repositorio para realizar operaciones CRUD sobre los alquileres.
- `com.velocity.back.controller`:
  - **BackController**: Controlador que maneja las solicitudes HTTP y gestiona el registro de usuarios, alquileres y otras características.

### Entidades y Repositorios

- **User**: Entidad que representa un usuario registrado.
- **Bike**: Entidad que representa una bicicleta registrada.
- **Club**: Entidad que representa un club.
- **Alquiler**: Entidad que representa un alquiler de bicicleta.

Los repositorios asociados a estas entidades permiten realizar operaciones CRUD sobre la base de datos H2 embebida.

## Funcionalidades Implementadas

1. **Registro de Usuarios y Bicicletas**:
   - A través del formulario en la página principal (`index.html`), se permite registrar un usuario y una bicicleta asociada. Los datos se almacenan en la base de datos H2.

2. **Gestión de Clubes**:
   - Los usuarios pueden inscribirse en un club proporcionando su ID de usuario y el nombre del club. La inscripción se almacena en la base de datos.
   - La página HTML `gestionClubes.html` contiene el formulario para la inscripción en un club.

3. **Gestión de Alquiler de Bicicletas**:
   - Los usuarios pueden alquilar una bicicleta proporcionando su ID de usuario y el ID de la bicicleta. El registro del alquiler se almacena en la base de datos.
   - La página HTML `gestionAlquiler.html` contiene el formulario para alquilar una bicicleta.

## Controlador Principal (`BackController`)

El controlador principal gestiona las solicitudes HTTP entrantes y redirige a las vistas correspondientes según las acciones solicitadas. Además, el controlador interactúa con los servicios de cada módulo para realizar las acciones de registro, inscripción y alquiler.

### Endpoints Importantes

- `/` - Muestra la página principal con el formulario de registro de usuario y bicicleta.
- `/gestionClubes` - Muestra la página para inscribirse en un club.
- `/inscribirClub` - Endpoint para procesar la inscripción de un usuario en un club.
- `/gestionAlquiler` - Muestra la página para alquilar una bicicleta.
- `/alquilarBicicleta` - Endpoint para procesar el alquiler de una bicicleta.

## Base de Datos H2

El proyecto utiliza una base de datos H2 embebida para almacenar los registros de usuarios, bicicletas, clubes y alquileres. La base de datos se configura automáticamente y se puede acceder a través de la consola H2 si está habilitada en la configuración de seguridad.

Para acceder a la consola de H2, puedes ir a `http://localhost:8080/h2-console` y asegurarte de que la configuración de seguridad permita el acceso.

## Configuración de Seguridad

Para permitir el acceso sin autenticación, se utiliza un `SecurityConfig` personalizado que deshabilita la seguridad para ciertos endpoints, como la consola de H2, y permite el acceso público a las páginas de inscripción y alquiler.

```java
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**", "/serviciosTerceros", "/gestionClubes", "/inscribirClub", "/gestionAlquiler", "/alquilarBicicleta").permitAll()
                .anyRequest().permitAll())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }
}
```

Esta configuración permite el acceso sin restricciones a los endpoints mencionados.

## Cómo Ejecutar el Proyecto

1. **Clonar el Repositorio**: Clona el repositorio en tu máquina local.
2. **Compilar el Proyecto**: Ejecuta `mvn clean package` para compilar el proyecto y generar el archivo `.jar`.
3. **Ejecutar la Aplicación**: Ejecuta `java -jar target/back-0.0.1-SNAPSHOT.jar` para iniciar la aplicación.
4. **Acceder a la Aplicación**: Abre un navegador y ve a `http://localhost:8080` para acceder a la página principal.

## Conclusión
Este proyecto demuestra cómo implementar una línea de productos de software en Spring Boot utilizando configuraciones para habilitar o deshabilitar características específicas. Esto permite generar diferentes versiones del producto de acuerdo a las necesidades del cliente, haciendo el proyecto flexible y escalable.

