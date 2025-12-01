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