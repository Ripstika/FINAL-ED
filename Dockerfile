# ===== ETAPA 1: COMPILACIÓN =====
FROM maven:3.9.6-eclipse-temurin-11 AS build

# Directorio de trabajo
WORKDIR /app

# Copiar pom.xml primero para aprovechar caché de Docker
COPY pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar proyecto
RUN mvn clean package -DskipTests

# ===== ETAPA 2: TOMCAT =====
FROM tomcat:9.0-jdk11-temurin

# Eliminar aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar WAR generado al ROOT de Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exponer puerto
EXPOSE 8080

# Iniciar Tomcat
CMD ["catalina.sh", "run"]
