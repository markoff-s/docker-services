# 1. create svc1 and containerize it

- /status - returns OK

- /calc - calculator

- /magic-op - multiples input param by the number retrieved from svc2

# 2. create comtainer svc2

- /status - OK

- / magic-op - returns const 7 which is retrieved from db container MySql/Postgres    

# 3. db container MySql/Postgres

- contains table with the constant which is reeteived by sv2

# 4. user docker-compose to start all containers, learn commands -e, -p, -it, run, exec
- check difference btw -e and CMD

# 5. push images to dockerhub private repo

# 6. Read about

- docker-swarm

- docker-machine

# 7. Read about

- dockerfile tuning - size, hierarchy

# 8. start up Jenkins

- configure containers build and push to dockerhub

- configure continers deploy

#9 mount volume to db

-----------------------------

# svc-mysql
docker build -t markoff/svc-mysql .
docker login
docker push markoff/svc-mysql
docker run -d --rm --name svc-mysql -p 3306:3306 markoff/svc-mysql

# svc-2
docker run -d --rm --name svc-2 -p 8082:8082 --link svc-mysql:mysql markoff/service-2

# svc-1
docker run -d --rm --name svc-1 --link svc-2 -p 8081:8081 markoff/service-1

# mysql-orig
docker run -d --rm --name svc-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testdb -e MYSQL_USER=user -e MYSQL_PASSWORD=user mysql

# jenkins
docker run -d --name svc-jenkins -p 8090:8080 -p 50000:50000 -v jenkins_home2:/var/jenkins_home jenkins/jenkins:lts


docker run -d --rm --name svc-2 -p 8082:8082 markoff/service-2
docker run -d --rm --name svc-1 -p 8081:8081 service-1
docker rmi 24461ea2fa0f
docker build -t service-1 /src/docker/dockerservices/service-1
docker image ls
docker exec -it svc-1 bash
netstat -tln
docker image prune
docker logs svc-mysql


# configure maven to build/push images
http://aerben.github.io/2017/docker-maven-1/
https://github.com/spotify/dockerfile-maven

# mysql image
https://hub.docker.com/_/mysql/