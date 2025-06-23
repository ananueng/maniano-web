FROM openjdk:21-jdk AS builder
WORKDIR /app
COPY ./mvnw ./mvnw
COPY ./.mvn ./.mvn
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN chmod +x ./mvnw
RUN ./mvnw clean package

FROM openjdk:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]