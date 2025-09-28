# ---- Build stage ----
FROM gradle:8.10.2-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle --no-daemon clean build -x test

# ---- Runtime stage ----
FROM ibm-semeru-runtimes:open-21.0.6_7-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/sporty-f1-0.1-all.jar sporty-f1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sporty-f1.jar"]
