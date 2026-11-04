# ==========================================
# ETAPA 1: BUILD (Compilación con Maven y Java 25)
# ==========================================
# Usamos una imagen oficial que contenga Maven y OpenJDK 25
FROM eclipse-temurin:25-jdk-noble AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos de configuración de dependencias
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargamos las dependencias (aprovechando el caché de capas de Docker)
RUN ./mvnw dependency:go-offline -B

# Copiamos el código fuente de la aplicación
COPY src ./src

# Compilamos y empaquetamos el proyecto omitiendo las pruebas unitarias locales (por el tema del socket de Docker)
RUN ./mvnw clean package -DskipTests

# ==========================================
# ETAPA 2: RUNTIME (Ejecución ligera con Java 25)
# ==========================================
# Usamos una imagen minimalista de JRE para producción
FROM eclipse-temurin:25-jre-noble

WORKDIR /app

# Creamos un usuario sin privilegios por seguridad (buena práctica enterprise)
RUN useradd -ms /bin/bash springuser
USER springuser

# Copiamos el archivo .jar generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto en el que corre Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación habilitando Java Virtual Threads de forma nativa
ENTRYPOINT ["java", "-XX:+UseZGC", "-XX:+ZGenerational", "-jar", "app.jar"]