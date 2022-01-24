# finalTaskJava

Описание API

Фильмы
GET http://localhost:8080/api/v1/films
GET http://localhost:8080/api/v1/films/{id}
POST http://localhost:8080/api/v1/films
PUT http://localhost:8080/api/v1/films/{id}
DELETE http://localhost:8080/api/v1/films/{id}


Кинотеатры
GET http://localhost:8080/api/v1/cinemas
GET http://localhost:8080/api/v1/cinemas/{id}
POST http://localhost:8080/api/v1/cinemas
PUT http://localhost:8080/api/v1/cinemas/{id}
DELETE http://localhost:8080/api/v1/cinemas/{id}


Актёры
GET http://localhost:8080/api/v1/actors
GET http://localhost:8080/api/v1/actors/{id}
POST http://localhost:8080/api/v1/actors
PUT http://localhost:8080/api/v1/actors/{id}
DELETE http://localhost:8080/api/v1/actors/{id}

Описание БД
url=jdbc:postgresql://127.0.0.1:5432/spring_cinema_test

Таблицы:
film
actor
cinema
country
genre
