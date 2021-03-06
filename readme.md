# API REST - Java

Rest API for product inventory control developed with Java Spring Boot. This project was make on "Microservice And Web Engineering" course from [Faculdade de Informática e Administração Paulista - FIAP](https://www.fiap.com.br/)

## Technology

- [Java Spring Boot](https://spring.io/projects/spring-boot)
- [Hibernate](https://hibernate.org/)
- [Kafka](https://kafka.apache.org/)
- [Docker](https://www.docker.com/)

### Clone
Clone the repository on your machine with the command

```
git clone https://github.com/jjeanjacques10/api-rest-java.git
```

### Config Database
Access the file **[application.properties](https://github.com/jjeanjacques10/api-rest-java/blob/master/src/main/resources/application.properties)**

```
spring.datasource.username={username}
spring.datasource.password={password}
```

### Run

First you need to start Kafka. After...

Run this file: **[ProdutosBootApplication](https://github.com/jjeanjacques10/api-rest-java/blob/master/src/main/java/br/com/fiap/ProdutosBootApplication.java)**

> Port: 8080

### Run Docker

Run project with Containers

```
./gradlew clean
./gradlew build

# Build Image
docker build -t produtos-boot:0.0.1 .

# Run Container
docker run -d --name produtos-boot -p 8080:8080 produtos-boot:0.0.1
```

## Endpoints

- /produtos 

- /categorias

- /marcas

Docs Endpoints: **[POSTMAN Docs](https://github.com/jjeanjacques10/api-rest-java/blob/master/src/main/resources/postman/Produtos%20Boot.postman_collection.json)**


--- 
developed by [Jean Jacques Barros](https://github.com/jjeanjacques10)