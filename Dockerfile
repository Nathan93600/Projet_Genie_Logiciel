FROM openjdk:17-slim

WORKDIR /app

COPY target/my-project-1.0.0.jar /app/my-project.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/my-project.jar"]
