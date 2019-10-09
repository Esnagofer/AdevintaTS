##Code Organization
The code is organized around maven modules, DDD & Hexagonal architecture.  
There is three modules  
**Application**: defines de use case  
**Domain**: model and services  
**Infrastructure**: Contains Ports&Adapters (not explicity named like this) for interaction with Application and Repository implementation    
##Module dependencies
We are using DDD so the model is the main concept.  
The Domain has no dependencies with the other modules but **LIB-DOMAIN** (see pom.xml dependencies).  
The Application it just depends on the Domain and **LIB-APPLICATION** (see pom.xml dependencies).  
The Infrastructure has dependencies on **Application**, **Domain** and **LIB-INFRASTRUCTURE** (see pom.xml dependencies).  
The same idea is applied for the LIB-xxx  
##Domain model
The indexation process implements "inverted index" modeled by Term aggregate which defines de relationship with the files
 in order to maximize the search efficiency.
##Known issues
Sorry but there's not testing (I had no time to implement it. The total amount of time I've dedicated is 5 hours).
I know that without testing it could not be deployed to a production environment.
I've not used TDD and I have prioritized the basic function & domain model. 
##How to compile
Requisites are Java 8+ & maven 3.6+
In the root project folder   
**mvn clean package**  
##How to execute
In the root project folder   
**java -jar core-textsearch-infrastructure/target/core-textsearch-infrastructure-RELEASE.jar {path to index files}**  
