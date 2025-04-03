###  PROJECT INITIALISATION
Created the project from spring initializr : (https://start.spring.io/index.html)

I initially disabled spring security and spring actuator to rapidly start the development
I use intellij idea text editor to run the project, in case you don't you can use the mvn spring-boot:run command

i am currently using a local postgres dbms but will later on transition to a postgres docker container

###  FUNCTIONALITIES 
The project is a core banking system:
It will be able to perform:
- account debits and crediting upon authorization and verification
- account balance calculation
- journal and ledger generation
- administrative overview and management of resources
- loggin/auditing of every actions
- etc

### DOCUMENTATION
Available swagger API documentation at http://localhost:8080/swagger-ui/index.html
OR json API documentation at http://localhost:8080/v3/api-docs