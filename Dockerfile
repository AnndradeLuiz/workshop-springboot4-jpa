# Estágio 1: Build (Compila o código usando o Maven e Java 25)
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# Estágio 2: Run (Roda a aplicação)
FROM eclipse-temurin:25-jre
WORKDIR /app
# Aqui colocamos o nome exato do arquivo que o seu pom.xml gera!
COPY --from=build /app/target/course-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]