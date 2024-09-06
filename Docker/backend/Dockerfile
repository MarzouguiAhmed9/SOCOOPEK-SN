# BUILD STAGE
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /BUILD
COPY pom.xml /BUILD
RUN mvn dependency:go-offline
COPY src /BUILD/src

RUN mvn clean package -DskipTests

# RUNTIME STAGE
FROM amazoncorretto:17
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /BUILD/target/secoopecproductnetwork-0.0.4-SNAPSHOT.jar /app/

# Set environment variables
ENV DB_URL=jdbc:postgresql://postgrescontainer24:5432/postgres

# Expose the application port
EXPOSE 8080

# Run the application with environment variables passed as JVM arguments
CMD ["java", "-jar", "-Dspring.datasource.url=${DB_URL}", "secoopecproductnetwork-0.0.4-SNAPSHOT.jar"]
