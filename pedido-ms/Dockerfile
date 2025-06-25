# --- ESTÁGIO 1: O Construtor (Builder) ---
# O contexto do build (definido no docker-compose.yml como '.') é a raiz do projeto 'ms-ecommerce'.
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia o pom.xml PAI.
COPY pom.xml .

# Copia o pom.xml do MÓDULO pedido-ms.
COPY pedido-ms/pom.xml ./pedido-ms/

# Baixa as dependências do MÓDULO pedido-ms.
RUN mvn -B -f pedido-ms/pom.xml dependency:go-offline

# Copia o código-fonte do MÓDULO pedido-ms.
COPY pedido-ms/src ./pedido-ms/src

# Compila e empacota o MÓDULO pedido-ms.
RUN mvn -B -f pedido-ms/pom.xml package -DskipTests


# --- ESTÁGIO 2: A Imagem Final (Runtime) ---
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho para o contêiner final.
WORKDIR /app

# Copia o .jar gerado do MÓDULO pedido-ms.
COPY --from=builder /app/pedido-ms/target/*.jar app.jar

# Documenta a porta interna do pedido-ms.
EXPOSE 8083

# Define o comando para executar a aplicação.
ENTRYPOINT ["java", "-jar", "app.jar"]