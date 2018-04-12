FROM openjdk:8-jre
 
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/dockerservices/service-1.jar
EXPOSE 8081

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/dockerservices/service-1.jar"]
