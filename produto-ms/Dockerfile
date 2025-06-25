# --- ESTÁGIO 1: O Construtor (Builder) ---
# O contexto do build (definido no docker-compose.yml como '.') é a raiz do projeto 'ms-ecommerce'.
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia o pom.xml PAI.
COPY pom.xml .

# Copia o pom.xml do MÓDULO produto-ms.
COPY produto-ms/pom.xml ./produto-ms/

# Baixa as dependências do MÓDULO produto-ms.
RUN mvn -B -f produto-ms/pom.xml dependency:go-offline

# Copia o código-fonte do MÓDULO produto-ms.
COPY produto-ms/src ./produto-ms/src

# Compila e empacota o MÓDULO produto-ms.
RUN mvn -B -f produto-ms/pom.xml package -DskipTests


# --- ESTÁGIO 2: A Imagem Final (Runtime) ---
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho para o contêiner final.
WORKDIR /app

# Copia o .jar gerado do MÓDULO produto-ms.
COPY --from=builder /app/produto-ms/target/*.jar app.jar

# Documenta a porta interna do produto-ms.
EXPOSE 8082

# Define o comando para executar a aplicação.
ENTRYPOINT ["java", "-jar", "app.jar"]