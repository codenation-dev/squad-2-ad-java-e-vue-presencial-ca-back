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
git clone https://github.com/codenation-dev/squad-2-ad-java-e-vue-presencial-ca-back.git
cd squad-2-ad-java-e-vue-presencial-ca-back
```

#### Develop

Run the application from the command line using:
```
mvn spring-boot:run
```

The H2 database will start running at http://localhost:8080/h2-console
The app will start running at <http://localhost:8080>


#### Test

Run the postgres database from the command line using: 
```
docker run -d --network=host \
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

The app will start running at <http://localhost:8080>

#### Production

Execute docker compose
```
docker-compose up
```

The app will start running at <http://localhost:8080>

## API Documentation

https://logstack-api.herokuapp.com/swagger-ui.html

## Explore Rest APIs

Namespace     |   URL                        | HTTP Verb        | Result 
--------------|----------------------------- | ---------------- | -------------------------
Logs          | /api/v1/logs                 | POST             | Add log
Logs          | /api/v1/logs                 | GET              | Return all logs
Logs          | /api/v1/logs/:id             | GET              | Return log by ID
Logs          | /api/v1/logs/:id             | DELETE           | Delete log
Logs          | /api/v1/logs/:id/archive     | PUT              | Archive log by ID
Logs          | /api/v1/logs/:id/archive     | DELETE           | Unarchive log by ID
--------------|----------------------------- | ---------------- | -------------------------
Triggers      | /api/v1/triggers             | POST             | Add Triggers
Triggers      | /api/v1/triggers             | GET              | Return all triggers
Triggers      | /api/v1/triggers/:id         | GET              | Return trigger by ID
Triggers      | /api/v1/triggers/:id         | PUT              | Update trigger by ID
Triggers      | /api/v1/triggers/:id/active  | PUT              | Active trigger by ID
Triggers      | /api/v1/triggers/:id/active  | DELETE           | Desactive trigger by ID
--------------|----------------------------- | ---------------- | -------------------------
Users         | /api/v1/users                | POST             | Add user
Users         | /api/v1/users                | GET              | Return all users
Users         | /api/v1/users/self           | GET              | Return user authenticated
--------------|----------------------------- | ---------------- | -------------------------

You can test them using postman or any other rest client.
