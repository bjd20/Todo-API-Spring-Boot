# Concepts Implemented through respective Commits:

## COMMIT 1:

###   Spring Boot Core + REST basics
* Installation of  JDK 17+, IDE and gradle.
* Project from Spring-Initializr with dependency "spring-boot-starter-web".
* Different MVC annotations like @RestController, @GetMapping, @PostMapping, path variables, request params, @RequestBody, etc.
* Dummy in-memory list database.
* DTOs for requests/responses.
  * Validations(@Valid, @NotNull, @Size).
  * Global exception handling using @ControllerAdvice.
* Clean structure: controller → service → repository(in-memory).


## COMMIT 2:

###   PostgreSQL + Spring Data JPA
* Replace the in-memory TodoRepository with the Spring Data JPA talking to a real PostgreSQL database.
* Dependencies of 'starter-jpa' and 'postgresql' added in build.gradle.
* application.properties: Database Configuration and JPA is setup.
* JPA Entity mapping (@Entity, @Id, @GeneratedValue).
* Spring Data JPA repositories (extending JpaRepository).
* Configuring DB connection in Spring Boot.
* Existing controller/service/DTO layers stay almost unchanged.
* New field added 'description'.
