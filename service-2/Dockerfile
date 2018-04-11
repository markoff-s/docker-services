FROM openjdk:8-jre

ARG JAR_FILE="service-2.jar"
ADD target/${JAR_FILE} /usr/share/dockerservices/service-2.jar
EXPOSE 8082

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/dockerservices/service-2.jar"]
