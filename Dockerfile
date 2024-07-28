# Etapa de build do frontend
FROM node:22 AS frontend-build
WORKDIR /frontend

# Copia o código-fonte do frontend
COPY frontend/package.json .
COPY frontend/package-lock.json .
RUN npm install -f
COPY frontend/ .

# Compila o frontend
RUN npm run build -- --output-path=dist

# Etapa de build do backend
FROM maven:3.9.8-amazoncorretto-21 AS backend-build
WORKDIR /backend

# Copia o código-fonte do backend
COPY backend/pom.xml .
COPY backend/mvnw .
COPY backend/mvnw.cmd .
COPY backend/.mvn .mvn

COPY --from=frontend-build /frontend/dist /backend/src/main/resources/static

COPY backend/src src

# Dá permissão de execução ao script mvnw
RUN chmod +x mvnw

# Compila o backend
RUN ./mvnw clean package -DskipTests

# Etapa final: Combina backend e frontend em um contêiner de produção
FROM maven:3.9.8-amazoncorretto-21 AS final
WORKDIR /app

# Copia o JAR do backend
COPY --from=backend-build /backend/target/*.jar app.jar

# Expõe a porta que o Spring Boot usa
EXPOSE 8080

# Comando para rodar o JAR do projeto
ENTRYPOINT ["java", "-jar", "app.jar"]
