FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/product-service.jar product-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "product-service.jar"]
