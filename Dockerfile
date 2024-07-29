# Etapa 1: Build do Frontend
FROM node:22 as build-frontend

WORKDIR /app/frontend

# Copiar todos os arquivos do frontend
COPY frontend/package*.json ./
COPY frontend/angular.json ./
COPY frontend/tsconfig*.json ./
COPY frontend/src ./src

# Instalar as dependências e buildar o frontend
RUN npm install --force
RUN npm run build --prod

# Etapa de build do backend
FROM maven:3.9.8-amazoncorretto-21 as build-backend
WORKDIR /backend

# Copia o código-fonte do backend
WORKDIR /app

# Copiar todos os arquivos do backend
COPY backend/pom.xml .
COPY backend/src ./src

# Copiar os arquivos buildados do frontend para a pasta resources/static do backend
COPY --from=build-frontend /app/frontend/dist/frontend/browser /app/src/main/resources/static

RUN chmod -R 755 /app/src/main/resources/static

# Buildar o backend
RUN mvn clean package -DskipTests

# Etapa final: Combina backend e frontend em um contêiner de produção
FROM maven:3.9.8-amazoncorretto-21 AS final
WORKDIR /app

# Copiar o JAR do backend buildado
COPY --from=build-backend /app/target/*.jar app.jar

# Expõe a porta que o Spring Boot usa
EXPOSE 8080

# Comando para rodar o JAR do projeto
ENTRYPOINT ["java", "-jar", "app.jar"]
