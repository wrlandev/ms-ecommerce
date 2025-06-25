# --- ESTÁGIO 1: O Construtor (Builder) ---
# Usamos uma imagem oficial do Maven com JDK 21 para compilar o código.
# O contexto do build (definido no docker-compose.yml como '.') é a raiz do projeto 'ms-ecommerce'.
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Define o diretório de trabalho dentro do contêiner.
WORKDIR /app

# Copia o pom.xml PAI (da raiz do projeto) para a raiz do WORKDIR no contêiner.
COPY pom.xml .

# Copia o pom.xml do MÓDULO cliente-ms para uma subpasta.
COPY cliente-ms/pom.xml ./cliente-ms/

# Baixa as dependências. Usamos '-f' para apontar para o pom.xml do MÓDULO.
# O Maven usará o 'relativePath' para encontrar o pom.xml pai que copiamos para a raiz.
RUN mvn -B -f cliente-ms/pom.xml dependency:go-offline

# Copia todo o código-fonte do módulo cliente-ms para sua respectiva subpasta no contêiner.
COPY cliente-ms/src ./cliente-ms/src

# Compila e empacota o projeto do módulo, gerando o .jar em /app/cliente-ms/target/
RUN mvn -B -f cliente-ms/pom.xml package -DskipTests


# --- ESTÁGIO 2: A Imagem Final (Runtime) ---
# Usamos uma imagem mínima, contendo apenas o Java Runtime Environment (JRE).
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho para o contêiner final.
WORKDIR /app

# Copia APENAS o .jar gerado do estágio "builder" para o contêiner final.
# Damos a ele um nome simples, "app.jar", para facilitar a execução.
COPY --from=builder /app/cliente-ms/target/*.jar app.jar

# Documenta a porta interna que a aplicação usa.
EXPOSE 8081

# Define o comando para executar a aplicação quando o contêiner iniciar.
ENTRYPOINT ["java", "-jar", "app.jar"]