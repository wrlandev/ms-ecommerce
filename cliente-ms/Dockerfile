# --- Estágio 1: Build ---
# Usa uma imagem do Maven com JDK 21 para compilar a aplicação.
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia o pom.xml pai e o pom.xml do módulo.
COPY ../pom.xml .
COPY cliente-ms/pom.xml ./cliente-ms/

# Baixa todas as dependências do projeto.
# Fazer isso em um passo separado aproveita o cache de camadas do Docker.
# Se o pom.xml não mudar, esta camada não será reexecutada.
RUN mvn -B -f cliente-ms/pom.xml dependency:go-offline

# Copia o código fonte do módulo.
COPY cliente-ms/src/ ./cliente-ms/src/

# Compila o projeto e gera o JAR, pulando os testes.
RUN mvn -B -f cliente-ms/pom.xml package -DskipTests

# --- Estágio 2: Runtime ---
# Usa uma imagem JRE (Java Runtime Environment) magra, pois não precisamos mais do JDK/Maven.
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho.
WORKDIR /app

# Copia o JAR gerado do estágio de build para o estágio de runtime.
COPY --from=builder /app/cliente-ms/target/*.jar app.jar

# Expõe a porta que a aplicação vai usar.
EXPOSE 8081

# Comando para executar a aplicação quando o contêiner iniciar.
ENTRYPOINT ["java","-jar","app.jar"]