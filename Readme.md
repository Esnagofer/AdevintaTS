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
##Known issues/TODO's/Improvements
The files to index might be readed line by line in order to avoid OutOfMemoryException for large files.  
The console message might be abstracted by a logger.  
Improve synchronization granularity.  
Index process might be executed concurrently (is thread safe).  
##How to compile
Requisites are Java 8+ & maven 3.6+
In the root project folder   
**mvn clean package**  
##How to execute
In the root project folder   
**java -jar core-textsearch-infrastructure/target/core-textsearch-infrastructure-RELEASE.jar {path to index files}**  
