# Assuming all the properties are set via the environment variables

docker build -t user-service ./user-service
docker build -t product-service ./product-service
docker build -t cart-service ./cart-service
docker build -t order-service ./order-service
docker build -t payment-service ./payment-service
docker build -t notification-service ./notification-service
docker build -t discovery-service ./discovery-service

docker run -d -p 8080:8080 user-service
docker run -d -p 8081:8081 product-service
docker run -d -p 8082:8082 cart-service
docker run -d -p 8083:8083 order-service
docker run -d -p 8084:8084 payment-service
docker run -d -p 8085:8085 notification-service
docker run -d -p 8761:8761 discovery-service