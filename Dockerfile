FROM maven:3.9.8-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:resolve

CMD ["mvn", "clean", "test"]