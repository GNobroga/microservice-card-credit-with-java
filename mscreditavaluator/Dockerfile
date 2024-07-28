FROM maven:3.8.5-openjdk-11 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:11

WORKDIR /workspace  

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
