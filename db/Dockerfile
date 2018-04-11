FROM mysql

# not used as of now, official mysql image is used, see docker-compose.yml
# passwords are for demo purposes, should be passed in on docker run
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=testdb
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=user

# https://hub.docker.com/_/mysql/ Initializing a fresh instance
ADD db_init.sql /docker-entrypoint-initdb.d

EXPOSE 3306