# Stage 1: Build the application using Gradle
#FROM gradle:8.5-jdk21 AS builder
FROM gradle:8.13-jdk21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]