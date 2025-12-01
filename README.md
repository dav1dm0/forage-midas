Task 1: Project Setup & Dependency Management
Objective: Configure the local development environment and establish the project's foundational dependencies.

Actions Taken:
Forked and cloned the repository.
Updated the pom.xml to include dependencies for Spring Boot, Kafka, H2 Database, and testing libraries.
Debugged version conflicts by downgrading the parent POM version to 3.2.4 to match the project scaffold.
Created a test configuration file to define required properties like general.kafka-topic.

Skills Trained: Build Automation (Maven), Dependency Management, Configuration Management, Troubleshooting Build Errors.

Files Created/Updated:
pom.xml (Updated dependencies and parent version)
src/test/resources/application.properties (Created for test configuration)

Technologies: Java 17, Maven, Spring Boot.


Task 2: Kafka Integration
Objective: Implement a Kafka Consumer to receive financial transactions asynchronously.

Actions Taken:
Created a MidasKafkaListener class using the @KafkaListener annotation to listen to the midas-transactions topic.
Configured Kafka consumer properties (Group ID, Serializers/Deserializers, Offset Reset) in application.properties to ensure proper message handling.

Skills Trained: Event-Driven Architecture, Message Queue Implementation, Asynchronous Programming, Debugging Distributed Systems.

Files Created/Updated:
src/main/java/com/jpmc/midascore/component/MidasKafkaListener.java (Created)
src/test/resources/application.properties (Updated consumer config)

Technologies: Apache Kafka, Spring Kafka.


Task 3: Database Integration & Business Logic
Objective: Persist user and transaction data using an in-memory SQL database and enforce validation rules.

Actions Taken:
Defined JPA Entities for UserRecord and TransactionRecord to map Java objects to database tables.
Created Repositories (UserRepository, TransactionRepository) extending CrudRepository for data access.
Implemented DatabaseConduit to handle business logic: validating sender balance and saving records.
Used Dependency Injection to wire the Repository and Database logic into the Kafka Listener.

Skills Trained: Object-Relational Mapping (ORM), Database Schema Design, Business Logic Implementation, Dependency Injection.

Files Created/Updated:
src/main/java/com/jpmc/midascore/entity/UserRecord.java (Created)
src/main/java/com/jpmc/midascore/entity/TransactionRecord.java (Created)
src/main/java/com/jpmc/midascore/repository/UserRepository.java (Created)
src/main/java/com/jpmc/midascore/repository/TransactionRepository.java (Created)
src/main/java/com/jpmc/midascore/component/DatabaseConduit.java (Updated)

Technologies: H2 Database, Spring Data JPA.


Task 4: External API Integration
Objective: Integrate a "black box" external Incentive API to modify transaction values.

Actions Taken:
Configured a RestTemplate bean to enable HTTP requests.
Created IncentiveApiService to act as a client for the external API.
Updated DatabaseConduit to call the API during transaction processing and adjust the recipient's balance with the incentive.
Resolved environment issues including Java runtime version mismatches and port conflicts.

Skills Trained: REST API Integration, Microservices Communication, Environment Troubleshooting, DTO (Data Transfer Object) Pattern.

Files Created/Updated:
src/main/java/com/jpmc/midascore/component/IncentiveApiService.java (Created)
src/main/java/com/jpmc/midascore/foundation/Incentive.java (Created)
src/main/java/com/jpmc/midascore/MidasCoreApplication.java (Updated)

Technologies: REST, Spring Web (RestTemplate).


Task 5: REST API Creation
Objective: Expose internal user balance data via a new REST endpoint.

Actions Taken:
Configured the application to run on a specific port (33400) via application.properties.
Implemented a BalanceController using @RestController to handle GET requests at /balance.
Connected the controller to the UserRepository to fetch live data.

Skills Trained: API Design (Controller Layer), REST Principles, Application Configuration.

Files Created/Updated:
src/main/java/com/jpmc/midascore/controller/BalanceController.java (Created)
src/main/resources/application.properties (Updated)

Technologies: Spring Web (Spring MVC), Embedded Tomcat.
