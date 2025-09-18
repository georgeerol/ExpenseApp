FROM maven:3.8.6-eclipse-temurin-8 AS build

# Install Node 20 LTS to build the CRA frontend via Maven exec plugin
RUN apt-get update && apt-get install -y curl gnupg && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && node -v && npm -v && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /workspace
COPY . .
RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:8-jre
WORKDIR /app
COPY --from=build /workspace/target/expense-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]


