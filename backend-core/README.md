
```
backend-core
├─ .dockerignore
├─ .mvn
│  └─ wrapper
│     └─ maven-wrapper.properties
├─ Dockerfile
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ backend_core
   │  │     ├─ BackendCoreApplication.java
   │  │     ├─ config
   │  │     │  ├─ CorsConfig.java
   │  │     │  ├─ OpenApiConfig.java
   │  │     │  └─ SecurityConfig.java
   │  │     ├─ controller
   │  │     │  └─ OnboardingController.java
   │  │     ├─ dto
   │  │     │  └─ OnboardingResourceDto.java
   │  │     ├─ exception
   │  │     │  ├─ ErrorResponseDto.java
   │  │     │  ├─ GlobalExceptionHandler.java
   │  │     │  └─ ResourceNotFoundException.java
   │  │     ├─ model
   │  │     │  └─ OnboardingResourceEntity.java
   │  │     ├─ repository
   │  │     │  └─ OnboardingRepository.java
   │  │     └─ service
   │  │        └─ OnboardingService.java
   │  └─ resources
   │     ├─ application.properties
   │     ├─ static
   │     └─ templates
   └─ test
      └─ java
         └─ backend_core
            ├─ BackendCoreApplicationTests.java
            └─ OnboardingRepositoryTest.java

```