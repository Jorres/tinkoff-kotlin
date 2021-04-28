FROM openjdk:11-slim

ADD build/libs/docker-second-0.0.1-SNAPSHOT-all.jar /usr/src/optional-rest.jar

WORKDIR /usr/src

EXPOSE 8080

ENTRYPOINT java -jar \
-DdbUrl=${MYSQL_DB_URL} \
-Dusername=${MYSQL_USER} \
-Dpassword=${MYSQL_PASSWORD} \
optional-rest.jar
