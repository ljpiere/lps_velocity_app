# Utiliza una imagen base de JDK 19
FROM eclipse-temurin:19-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado a la imagen Docker
COPY target/back-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
