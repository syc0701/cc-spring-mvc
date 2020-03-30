# Welcome to Crafter Codebase

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

    Service         | Mysql           | Adminer       | Web Server        
    --------------- | --------------- | ------------- | -------------
    IP Address      | 172.16.1.10     | 172.16.1.11   | 172.16.1.20
    Port            | 3360            | 9000          | 8000
    User / Password | ccuser / ccpass | root / ccpass | N/A


## How to use it.
1. Mysql & Adminer 실행

    docker-compose 를 이용하여 Mysql 서비스와 Adminer서비스를 실행한다.
    
    >__$ docker-compose -f cc-mysql-adminer-compose.yml up -d__
    
   당신의 컴퓨터에 mysql client가 설치되어 있다면, mysql은 아래와 같이 확인하세요
    >$ mysql -h 172.16.1.10 -u root -p
  
    Adminer는 웹 브라우저에서 아래와 같이 확인하세요. 연결계정은 root / ccpass를 입력한다.
    >http://localhost:9000
    
    
    Mysql에 데이터베이스베이스와 사용자를 스크립트를 실행하여 넣는다.
    >$ mysql -h 172.16.1.10 -u root -p < cc-mysql-inital.sql
    
    ~~~cc-mysql-inital.sql
    create database db_craftercodebase;                 
    create user 'ccuser'@'%' identified by 'ccpass';    
    grant all on db_craftercodebase.* to 'ccuser'@'%';  
    ~~~
    
1. 코드를 컴파일 하고 패키지 하여 jar 파일을 생성한다.
    >__mvn package__
    
1. Web 서비스 실행
    >__java -jar target\cc-spring-mvc-0.0.1-SNAPSHOT.jar__

1. Web Service 확인    
    >__http://localhost:8000/demo/all__


## Docker Image 만들기

    위 웹서비스가 실행되면 웹서비스를 Docker 이미지로 만들어서 확인한다.

1, Maven Build
    Java소스코드를 Maven을 이용하여 jar 파일로 패키지 한다.
    
    >__$ mvn package__

1. Docker 빌드
    >__$ docker build -f cc-spring-mvc-docker -t cc-spring-mvc .__
    
    cc-spring-mvc-docker 의 내용
    ~~~
    FROM openjdk:8-jdk-alpine
    COPY target/*.jar /app.jar
    ENTRYPOINT ["java","-jar","/app.jar"]
    ~~~

1. Docker 이미지로 만들어진 Web서비스를 실행한다.
    >__docker run --name cc-spring-mvc_web_1 --network frontend --rm --ip 172.16.1.20 -p 8080:8080 -t cc-spring-mvc__
    
