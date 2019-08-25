# Centralizador de Erros REST API

## Getting Started

### Prerequisites
- Git
- Maven 3.0+
- JDK 8+
- Docker 1.13.0+
- Docker Compose 1.23.1+

### Clone

To get started you can simply clone this repository using git:
```
git clone https://github.com/codenation-dev/squad-2-ad-java-e-vue-presencial-ca.git
cd squad-2-ad-java-e-vue-presencial-ca/backend
```

#### Develop

Run the application from the command line using:
```
mvn spring-boot:run
```

#### Test

Run the postgres database from the command line using: 
```
docker run -d  --network=host\
    --name central-erros-db-test \
    -e POSTGRES_DB=db \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
   postgres:10.4 
```

Run the application from the command line using:
```
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

To Stop the postgres database from the command line using:
```
docker stop central-erros-db-test
```

#### Production
Execute docker compose
```
docker-compose up
```


The app will start running at <http://localhost:8080>.

## API Documentation

http://localhost:8080/api/swagger-ui.html

## Explore Rest APIs

URL                       | HTTP Verb      | Result 
------------------------- | -------------- | -------------
/api/errors               | POST           | Add error
/api/errors               | GET            | Return all errors
/api/errors/:id           | GET            | Return error by ID
/api/errors/:id           | DELETE         | Delete error
/api/errors/:id/archive   | POST           | Archive error
/api/errors/:id/unarchive | POST           | Unarchive error

You can test them using postman or any other rest client.
