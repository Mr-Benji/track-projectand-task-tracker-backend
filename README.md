# Track Project and Task Tracker Backend

Project name: Track Project and Task Tracker (`track-projectand-task-tracker`)

## Authors

| Name | Student ID |
|---|---|
| Fathiri Benjamin | 27393 |

## Tech stack

Java 21, Spring Boot 4, Spring Web, Spring Data JPA, PostgreSQL

## How to run the API

1. Install Java 21 (or newer) and PostgreSQL.
2. Create the database:
   ```sql
   CREATE DATABASE tracker_db;
   ```
3. Open `src/main/resources/application.properties` and set your own PostgreSQL username and password:
   ```properties
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```
4. From the project folder, start the application:
   - Windows: `.\mvnw spring-boot:run`
   - macOS / Linux: `./mvnw spring-boot:run`

   Hibernate creates all the tables automatically (`ddl-auto=update`).
5. The API is available at `http://localhost:8080`.
6. In Postman, click "Import" and select `track-projectand-task-tracker-midterm.postman_collection.json` from this repository to test every requirement.