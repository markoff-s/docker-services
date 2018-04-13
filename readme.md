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

# 9 mount volume to db

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

# jenkins https://getintodevops.com/blog/the-simple-way-to-run-docker-in-docker-for-ci
1. start jenkins with container's docker socket mapped to host's docker socket
<code>
docker run -d --name svc-jenkins -p 8090:8080 -p 50000:50000 `
-v jenkins_home2:/var/jenkins_home `
-v /var/run/docker.sock:/var/run/docker.sock `
jenkins/jenkins:lts
</code>

2. log into jenkins as root and execute the magic abracadabra to install docker ce 
<code>docker exec -it -u root svc-jenkins bash</code>

<code>
apt-get update && \
apt-get -y install apt-transport-https \
     ca-certificates \
     curl \
     gnupg2 \
     software-properties-common && \
curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
   $(lsb_release -cs) \
   stable" && \
apt-get update && \
apt-get -y install docker-ce
</code>

3. <code>docker ps -a</code> to check you can access host's docker now

4. run maven build in jenkins now and get Permission denied to the socket error. "root" can successfully access the socket, 
however, "jenkins" can not. some proposals on the web recommend to add "jenkins" to the "docker" group and then make sure to
log out/in or reboot. <code>usermod -a -G docker jenkins</code> - i did that, it didn't work. further investigation led 
to another proposal - open read/write/exec access to the socket to all - <code>chmod 777 /var/run/docker.sock</code>.
this time it worked, although, very insecure and cannot be used in production. 
for more details see <link>https://github.com/jenkinsci/docker/issues/263</link>

5. now you can run maven build on jenkins provided you configured jdk and maven installations in jenkins Global Tools 
Configuration. At this stage, maven build and push to docker hub work succefully

6. will need to create a personal jenkins image with jdk, maven and docker setup and configuration all in one Dockerfile

-------------------

# some useful commands
<code>
docker run -d --rm --name svc-2 -p 8082:8082 markoff/service-2
docker run -d --rm --name svc-1 -p 8081:8081 service-1
docker rmi 24461ea2fa0f
docker build -t service-1 /src/docker/dockerservices/service-1
docker image ls
docker exec -it svc-1 bash
netstat -tln
docker image prune
docker logs svc-mysql
docker exec -it -u root svc-jenkins bash
</code>


# configure maven to build/push images
http://aerben.github.io/2017/docker-maven-1/
https://github.com/spotify/dockerfile-maven

# mysql image
https://hub.docker.com/_/mysql/

# The simple way to run Docker-in-Docker for CI
https://getintodevops.com/blog/the-simple-way-to-run-docker-in-docker-for-ci
# Use docker inside docker with jenkins user
https://github.com/jenkinsci/docker/issues/263
https://techoverflow.net/2017/03/01/solving-docker-permission-denied-while-trying-to-connect-to-the-docker-daemon-socket/