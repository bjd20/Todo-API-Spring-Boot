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

---

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

---

## COMMIT 3:

### Multi-User + Authentication(JWT)
* User JPA entity and DTOs for User requests and responses.
* JPA relationships: One-to-Many (User ↔ Todo).
* Spring Security fundamentals and configuration.
* JWT (JSON Web Tokens) for stateless authentication.
* Basic role-based access control (RBAC).
* Unit and integration tests using JUnit 5 and Mockito.


```
Single Todo List          →         Multi-User
──────────────────────────────────────────────────

API Endpoint                       HTTP Request
    ↓                                 ↓
TodoController                  Authentication Filter (JWT)
    ↓                                 ↓
TodoService                     User extracted from token
    ↓                                 ↓
TodoRepository                  UserContext / SecurityContext
    ↓                                 ↓
Todo (no user)          →    Todo (belongs to User)
                                    ↓
                        User filters todos by user_id
                                    ↓
                        Only authorized user can see/edit
```
**Insight**: Every todo now belongs to a user. Security layer sits at the HTTP boundary.

---