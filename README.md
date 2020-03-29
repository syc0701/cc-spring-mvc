# Welcome to CC-Spring-MVC

Spring Starter와 Mysql을 기반으로하는 프로젝트를 진행할때 필요한 CC(Crafter Codebase) 입니다.
Docker를 이용하여 구동되기 때문에 편리합니다.

이 모듈은 Windows기반의 Docker를 실행합니다.

## Network
모듈을 실행하기 위해 다음 네트워크를 구성한다.

* Create CC Network

    $ docker network create -d nat --subnet=172.16.1.0/24 --gateway=172.16.1.1 frontend

    ~~~
    $ docker network ls
    NETWORK ID          NAME                DRIVER              SCOPE
    155f8469d9a1        frontend              nat                 local
    ~~~

* IP Configuration

    Mysql           | Adminer       | Web Server        
    --------------- | ------------- | -------------
    172.16.1.10     | 172.16.1.11   | 172.16.1.20
    ccuser / ccpass | root / ccpass | N/A


## How to use it.
1. Mysql & Adminer 실행
    ~~~
    $ docker-compose -f cc-mysql-adminer-compose.yml up
    ~~~
    
   당신의 컴퓨터에 mysql client가 설치되어 있다면, mysql은 아래와 같이 확인하세요
    __$ mysql -h 172.16.1.10 -u root -p__
  
    Adminer는 웹 브라우저에서 아래와 같이 확인하세요.
    __http://localhost:9000*__
    

    
1. compose file을 실행해서 Mysql 과 Webservice를 실행한다.
    - Mysql 스크립트 를 실행해서 DB와 Table도 만든다.
    

1. cc-spring-mvc 를 Docker Image로 만듬..
* 8080 포트를 사용할 수 있도록 해야함.
* docker run --rm cc/spring-mvc:0.1 -p 8080:8080
* 이미지기 실행되면서 컨테이너의 IP 가 자동으로 할당되서 IP를 확인해야함. (docker inspect CONTAINER_ID)


1. Maven Build
*  mvnw package

1. Run
*  java -jar target/cc-spring-docker-0.1.0.jar


## Docker Image 만들기

1, Maven Build
    ~~~
    $ mvnw package
    ~~~

1. Docker file : cc-spring-mvc-docker
    ~~~
    FROM openjdk:8-jdk-alpine
    ARG JAR_FILE=target/*.jar
    COPY ${JAR_FILE} app.jar
    ENTRYPOINT ["java","-jar","/app.jar"]
    ~~~

1. Docker 빌드
    docker build -t springio/gs-spring-boot-docker .

1. Docker 실행
    docker run -p 8080:8080 -t springio/gs-spring-boot-docker
    
    
    

## License





