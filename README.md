# Welcome to Crafter Codebase

This project ('cc-spring-mvc') can be used to start web programming. You can use it and modify what you want.

This project is a web application that runs using Spring Boot. It can store and access data in Mysql using JPA. 
In addition, Display data using Thymeleaf Template.

In the list view, the data is displayed using Bootstrap Datatable. 
Input forms are validated using jQuery Validation.

To run this project, [Docker](https://www.docker.com/) must be installed on your system.

## Application environment
* Java 1.8.0
* Mysql 8.0.19
* Spring 2.2.6.RELEASE
* Docker 19.03.8
* [Apache Maven 3.6.3](https://getbootstrap.com)
* [Bootstrap 4.4.1](https://getbootstrap.com)
* [WebJars](https://www.webjars.org/)
* [jQuery Validation 1.19.0](https://jqueryvalidation.org/)
* [Boorstrap table 1.16.0](https://bootstrap-table.com/)
* [Concept Bootstrap 4 Admin Dashboard Template](https://colorlib.com/polygon/concept/index.html)


## Network Configurations
To perform this project, a private network must be used.
Because the Docker assigns different IPs to containers at execution time, it is effective to use static IPs in the development process.

* Create a private network

>__$ docker network create -d nat --subnet=172.16.1.0/24 --gateway=172.16.1.1 frontend__

```
$ docker network ls
NETWORK ID          NAME                DRIVER              SCOPE
155f8469d9a1        frontend              nat                 local
```

* IPs and Ports

    Service         | Mysql           | Adminer       | Web Server        
    --------------- | --------------- | ------------- | -------------
    IP Address      | 172.16.1.10     | 172.16.1.11   | localhost
    Port            | 3360            | 9000          | 8080
    User / Password | ccuser / ccpass | root / ccpass | N/A


## How to use it
	1. Running MySQL and Adminer service.
	
	>__$ docker-compose -f cc-mysql-adminer-compose.yml up -d__
	
	2. Connect to [Adminer](http://localhost:9000) with a web browser.
	
	
	1. The 'db_craftercodebase' database and users in Mysql can be created by running the following command.
	
	>__$ mysql -h 172.16.1.10 -u root -p < cc-mysql-inital.sql__
	
	```
	create database db_craftercodebase;                 
	create user 'ccuser'@'%' identified by 'ccpass';    
	grant all on db_craftercodebase.* to 'ccuser'@'%';  
	```
	
	1. Compile and package this project to create a jar file.
	
	>__$ mvn package__
	    
	1. Run the cc-spring-mvc web service
	
	>__$ java -jar target\cc-spring-mvc-0.0.1-SNAPSHOT.jar__
	
	1. Access to web service.
	
	>__[http://localhost:8080](http://localhost:8080)__


## Optional: Create Docker Image
	
	It is useful to test by creating a Docker image before deploying a web project. We can test that the package is working properly.
	
	1. Package the source code and resources into a jar file.
	    
	>__$ mvn package__
	
	1. Creating a docker image
	
	>__$ docker build -f cc-spring-mvc-docker -t cc-spring-mvc .__
	    
	
	```
	FROM openjdk:8-jdk-alpine
	COPY target/*.jar /app.jar
	ENTRYPOINT ["java","-jar","/app.jar"]
	```
	
	1. Run the web service with the created docker image
	
	>__$ docker run --name cc-spring-mvc_web_1 --network frontend --rm --ip 172.16.1.20 -p 8080:8080 -t cc-spring-mvc__


## Contact
If you have any questions or requests, please contact me at [syc0701@gmail.com](mailto:syc0701@gmail.com)    