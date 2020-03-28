# Welcome to CC-Spring-MVC

Spring Starter와 Mysql을 기반으로하는 프로젝트를 진행할때 필요한 코드 베이스 입니다.
Docker를 이용하여 구동되기 때문에 편리합니다.

## What's New
* Create CC Network


    | $ docker network create -d nat --subnet=172.16.1.0/24 --gateway=172.16.1.1 cc-net |
    -----------------------------------------------------------------------------------

    
    $ docker network ls
    NETWORK ID          NAME                DRIVER              SCOPE
    155f8469d9a1        cc-net              nat                 local

    Mysql         | Web Server        
    ------------- | -------------
    172.16.1.10   | 172.16.1.20

* Item 2
  * Item 2a
  * Item 2b


## How to use it.
1. compose file을 실행해서 Mysql 과 Webservice를 실행한다.
    - Mysql 스크립트 를 실행해서 DB와 Table도 만든다.
2. 

## License


cc-spring-mvc 를 Docker Image로 만듬..
-> 8080 포트를 사용할 수 있도록 해야함.
-> docker run --rm cc/spring-mvc:0.1 -p 8080:8080
-> 이미지기 실행되면서 컨테이너의 IP 가 자동으로 할당되서 IP를 확인해야함. (docker inspect CONTAINER_ID)


