# LPS Velocity App
# Proyecto Spring Boot con Maven y Thymeleaf

Daniel Montenegro
Ricardo Cordoba
Leyton Castro

Este proyecto es una aplicación web construida con Spring Boot y Maven, que utiliza Thymeleaf para generar la interfaz de usuario. La aplicación permite habilitar ciertas características definidas en clases y mostrarlas en una página web HTML. Esta documentación te guiará a través de los componentes principales del proyecto y cómo lograr que funcione correctamente.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje principal de desarrollo.
- **Spring Boot 3**: Framework utilizado para la creación de aplicaciones basadas en Java.
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **Thymeleaf**: Motor de plantillas para renderizar páginas HTML.
- **HTML/CSS**: Utilizados para la interfaz de usuario.

## Estructura del Proyecto

El proyecto tiene varios componentes clave:

1. **Controlador** (`FeatureController.java`): Este controlador se encarga de manejar las solicitudes HTTP y pasar los datos relevantes al modelo que luego se renderizará en la vista.

2. **Configuración de Características** (`FeatureConfig.java`): Define qué características están habilitadas en el producto final.

3. **Clases de Características** (`RegistroCiclistas.java`): Implementa las lógicas individuales de las características habilitadas, como "Registro de Ciclistas".

4. **Vista HTML** (`index.html`): Utiliza Thymeleaf para renderizar las características habilitadas en la página web.

## Guía de Implementación

### 1. Controlador (FeatureController)

El controlador es el encargado de procesar las solicitudes HTTP y proporcionar los datos necesarios a la vista. Asegúrate de tener un método como el siguiente en `FeatureController.java`:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeatureController {

    @Autowired
    private FeatureConfig featureConfig;

    @GetMapping("/")
    public String getFeatureDescriptions(Model model) {
        List<String> featureDescriptions = featureConfig.getEnabledFeatures();
        model.addAttribute("featureDescriptions", featureDescriptions);
        return "index";
    }
}
```

Este método agrega una lista de descripciones de características al modelo, que será usado por Thymeleaf para mostrarlas en la página web.

### 2. Configuración de Características (FeatureConfig)

La clase `FeatureConfig` define las características habilitadas. Puedes definir estas características estáticamente o cargar configuraciones desde un archivo externo:

```java
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FeatureConfig {

    public List<String> getEnabledFeatures() {
        return List.of("Registro de Ciclistas habilitado");
    }
}
```

Este ejemplo proporciona una lista estática de características habilitadas, pero podrías extenderlo para cargar configuraciones dinámicas.

### 3. Clase de Característica (RegistroCiclistas)

`RegistroCiclistas.java` representa una característica específica. Puedes definir métodos que describan la funcionalidad proporcionada:

```java
import org.springframework.stereotype.Component;

@Component
public class RegistroCiclistas {

    public String getDescription() {
        return "Registro de Ciclistas habilitado";
    }
}
```

En `FeatureController`, puedes usar este método para agregar la descripción al modelo que se pasa a la vista.

### 4. Vista HTML (index.html)

El archivo `index.html` utiliza Thymeleaf para mostrar las características habilitadas:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Características Habilitadas</title>
</head>
<body>
    <h1>Características del Producto Compilado</h1>
    <ul>
        <li th:each="description : ${featureDescriptions}" th:text="${description}"></li>
    </ul>
</body>
</html>
```

Este archivo renderiza una lista de características basadas en el modelo proporcionado por el controlador.

## Ejecución del Proyecto

1. **Compilar el Proyecto**:
   Ejecuta el siguiente comando para compilar el proyecto utilizando Maven:
   ```
   mvn clean install
   ```

2. **Ejecutar la Aplicación**:
   Inicia la aplicación ejecutando:
   ```
   mvn spring-boot:run
   ```

3. **Ver la Página HTML**:
   Abre un navegador web y navega a `http://localhost:8080/` para ver la lista de características habilitadas.

## Solución de Problemas

- **Características no mostradas**: Si no ves las características en la página, verifica que el `FeatureController` esté agregando correctamente las características al modelo y que Thymeleaf esté configurado correctamente en tu archivo `application.properties`.
- **Dependencias faltantes**: Asegúrate de tener las dependencias de Thymeleaf y Spring Web en tu archivo `pom.xml`.

## Dependencias en `pom.xml`

Asegúrate de tener las siguientes dependencias en tu archivo `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

Estas dependencias son esenciales para que Spring Boot pueda utilizar Thymeleaf y manejar solicitudes web.

## Resumen

Este proyecto te permite definir y habilitar diferentes características usando clases Java y mostrarlas en una página web. Con el uso de Spring Boot, Maven y Thymeleaf, puedes tener una implementación flexible y modular para mostrar las características que desees habilitar en tu producto compilado.

Si tienes más preguntas o necesitas ayuda adicional, no dudes en consultarme.

