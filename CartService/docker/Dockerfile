FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/cart-service.jar cart-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "cart-service.jar"]
