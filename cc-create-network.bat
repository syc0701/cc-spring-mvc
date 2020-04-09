REM
REM All necessary services in cc-spring-mvc project are connected in 172.16.1.0/24 network.
REM You must run this batch file before running Mysql and Adminer.
REM
docker network create -d nat --subnet=172.16.1.0/24 --gateway=172.16.1.1 frontend
