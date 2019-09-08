# LogStack

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
docker run -d --network=host\
    --name logstack-db \
    -e POSTGRES_DB=logstack \
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
docker stop logstack-db
```

To Start again the postgres database from the command line using:
```
docker start logstack-db
```

#### Production
Execute docker compose
```
docker-compose up
```

The app will start running at <http://localhost:8080>.

## API Documentation

http://localhost:8080/swagger-ui.html

## Explore Rest APIs

URL                        | HTTP Verb        | Result 
-------------------------- | ---------------- | -------------
/api/v1/logs               | POST             | Add log
/api/v1/logs               | GET              | Return all logs
/api/v1/logs/:id           | GET              | Return log by ID
/api/v1/logs/:id           | DELETE           | Delete log
/api/v1/logs/:id/archive   | PUT              | Archive log by ID
/api/v1/logs/:id/archive   | DELETE           | Unarchive log by ID

You can test them using postman or any other rest client.
