FROM maven:latest as build
# Copy only pom.xml of your projects and download dependencies
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:go-offline
# Copy all other project files and build project
COPY . .
RUN mvn -B install -DskipTests

# Run stage
FROM openjdk:17-alpine
COPY --from=build ./target/*.jar ./
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/EfecteBack-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080