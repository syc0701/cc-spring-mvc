# Welcome to Crafter Codebase

This project ('cc-spring-mvc') can be used to start web programming. You can use it and modify what you want.

This project is a web application that runs using Spring Boot. It can store and access data in Mysql using JPA. 
In addition, Display data using Thymeleaf Template.

In the list view, the data is displayed using Bootstrap Datatable. 
Input forms are validated using jQuery Validation.

To run this project, docker must be installed on your system.

## Development environment
* Java 1.8.0
* Apache Maven 3.6.3
* Mysql 8.0.19
* Spring 2.2.6.RELEASE
* Bootstrap 4.4.1
* WebJars
* jQuery Validation 1.19.0


## Network
To perform this project, a fixed network IP must be used.
Because Docker assigns different IPs to containers at each execution time, it is effective to use static IPs in the development process.

* Create a private network

>$ docker network create -d nat --subnet=172.16.1.0/24 --gateway=172.16.1.1 frontend

    ```
    $ docker network ls
    NETWORK ID          NAME                DRIVER              SCOPE
    155f8469d9a1        frontend              nat                 local
    ```

* IP / Port configuration

    Service         | Mysql           | Adminer       | Web Server        
    --------------- | --------------- | ------------- | -------------
    IP Address      | 172.16.1.10     | 172.16.1.11   | localhost
    Port            | 3360            | 9000          | 8080
    User / Password | ccuser / ccpass | root / ccpass | N/A


## How to use it.
1. Running MySQL and admin

>Run Mysql service and Adminer service using docker-compose.
    
>__$ docker-compose -f cc-mysql-adminer-compose.yml up -d__

>If you have a mysql client installed on your computer, you can test the connection to mysql.
>__$ mysql -h 172.16.1.10 -u root -p__
  
>Please check the adminer as below in the web browser. For the linked account, The account is root and it's password is ccpass.
>__http://localhost:9000__

> The 'db_craftercodebase' database and users in Mysql can be created by running the following command.
>__$ mysql -h 172.16.1.10 -u root -p < cc-mysql-inital.sql__

```
create database db_craftercodebase;                 
create user 'ccuser'@'%' identified by 'ccpass';    
grant all on db_craftercodebase.* to 'ccuser'@'%';  
```
    
1. Compile and package this project to create a jar file.
    >__mvn package__
    
1. Run the cc-spring-mvc web service
    >__java -jar target\cc-spring-mvc-0.0.1-SNAPSHOT.jar__

1. Access to web service.
    >__http://localhost:8080


## Optional: Create Docker Image
	
> It is useful to test by creating a Docker image before deploying a web project. We can test that the package is working properly.

1. Maven Build
Package the source code and resources into a jar file.
    
>__$ mvn package__

1. Creating a docker image
>__$ docker build -f cc-spring-mvc-docker -t cc-spring-mvc .__
    

```dockerfile
FROM openjdk:8-jdk-alpine
COPY target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

1. Run the web service with the created docker image
>__docker run --name cc-spring-mvc_web_1 --network frontend --rm --ip 172.16.1.20 -p 8080:8080 -t cc-spring-mvc__


## Contact
If you have any questions or requests, please contact me at [syc0701@gmail.com](mailto:syc0701@gmail.com)    