# Etapa 1: Compilar el proyecto con Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copiar archivos de configuración y código fuente
COPY pom.xml .
COPY src ./src

# Compilar el proyecto omitiendo los tests para agilizar el despliegue
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecución (ligera)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar el .jar generado en la Etapa 1
COPY --from=build /app/target/*.jar app_pruebatecsuper.jar

EXPOSE 8080

# Parámetro recomendado para optimizar el consumo de memoria en la capa gratuita de Render (512MB RAM)
ENTRYPOINT ["java", "-Xmx380m", "-jar", "app_pruebatecsuper.jar"]