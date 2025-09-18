FROM maven:3.8.6-eclipse-temurin-8 AS build

WORKDIR /workspace
COPY . .
# Skip frontend exec plugin during container build
RUN mvn -B -DskipTests -Dskip.frontend=true clean package

FROM eclipse-temurin:8-jre
WORKDIR /app
COPY --from=build /workspace/target/expense-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]


