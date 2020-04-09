# Welcome to Crafter Codebase

웹 프로그래밍을 시작할때 사용할 수 있는 Code base 입니다. 
코드를 수정하거나 추가해서 사용하시면 됩니다. 

cc-spring-mvc 모듈은 Spring Boot를 이용하여 구동되는 웹 어플리케이션 입니다.
Spring MVC를 이용하여 Mysql 에 데이터를 저장하고 엑세스 할 수 있습니다. 또한  View는 
Thymeleaf의 Template를 이용하여 데이터를 표시합니다.

리스트 뷰에서는 Bootstrap Datatable를 이용하여 데이터를 표시합니다. 
입력폼에서는 jQuery Validataion 을 이용하여 유효성 검사를 합니다.


## 개발 환경
* Java 1.8.0
* Mysql 8.0.19
* Spring 2.2.6.RELEASE
* Bootstrap 4.4.1
* Apache Maven 3.6.3
* WebJars
* jQuery Validation 1.19.0


## Network 환경
CC의 MVC모듈을 수행하기 위해서는 고정된 네트워크IP를 이용하여야 한다.
Docker는 실행행시마다 다른 IP를 Container에 부여하기 때문에 개발과정에서는 고정 IP를 이용하는것이 효과적이다.

* Create CC Network

>$ docker network create -d nat --subnet=172.16.1.0/24 --gateway=172.16.1.1 frontend

    ~~~
    $ docker network ls
    NETWORK ID          NAME                DRIVER              SCOPE
    155f8469d9a1        frontend              nat                 local
    ~~~

* IP Configuration

    Service         | Mysql           | Adminer       | Web Server        
    --------------- | --------------- | ------------- | -------------
    IP Address      | 172.16.1.10     | 172.16.1.11   | localhost
    Port            | 3360            | 9000          | 8080
    User / Password | ccuser / ccpass | root / ccpass | N/A


## How to use it.
1. Mysql & Adminer 실행
Mysql과  이를 제어할 수 있는 Adminier를 실행합니다. 

>docker-compose 를 이용하여 Mysql 서비스와 Adminer서비스를 실행한다.
    
>__$ docker-compose -f cc-mysql-adminer-compose.yml up -d__

>당신의 컴퓨터에 mysql client가 설치되어 있다면, mysql은 아래와 같이 확인하세요.
>__$ mysql -h 172.16.1.10 -u root -p__
  
>Adminer는 웹 브라우저에서 아래와 같이 확인하세요. 연결계정은 root / ccpass를 입력한다.
>__http://localhost:9000__


> Mysql에 데이터베이스베이스와 사용자를 스크립트를 실행하여 넣는다.

>__$ mysql -h 172.16.1.10 -u root -p < cc-mysql-inital.sql__

```
create database db_craftercodebase;                 
create user 'ccuser'@'%' identified by 'ccpass';    
grant all on db_craftercodebase.* to 'ccuser'@'%';  
```
    
1. 코드를 컴파일 하고 패키지 하여 jar 파일을 생성한다.
    >__mvn package__
    
1. Web 서비스 실행
    >__java -jar target\cc-spring-mvc-0.0.1-SNAPSHOT.jar__

1. Web Service 확인    
    >__http://localhost:8000


## 선택사항 : Docker Image 만들기
	
> CC Web 모듈을 배포하기 전에 Docker 이미지로 만들어 테스트해보는것이 유용하다.
  위 웹서비스가 실행되면 웹서비스를 Docker 이미지로 만들어서 확인한다.

1. Maven Build
Java소스코드를 Maven을 이용하여 jar 파일로 패키지 한다.
    
>__$ mvn package__

1. Docker 빌드
>__$ docker build -f cc-spring-mvc-docker -t cc-spring-mvc .__
    

```dockerfile
FROM openjdk:8-jdk-alpine
COPY target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

1. Docker 이미지로 만들어진 Web서비스를 실행한다.
>__docker run --name cc-spring-mvc_web_1 --network frontend --rm --ip 172.16.1.20 -p 8080:8080 -t cc-spring-mvc__
    