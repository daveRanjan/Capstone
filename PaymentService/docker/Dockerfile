FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/payment-service.jar payment-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
