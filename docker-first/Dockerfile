FROM openjdk:11-slim

ADD build/libs/docker-first-0.0.1-SNAPSHOT-all.jar /usr/src/rest.jar

WORKDIR /usr/src

EXPOSE 8080

ENTRYPOINT java -jar \
-DdbUrl=${POSTGRES_DB_URL} \
-Dusername=${POSTGRES_USER} \
-Dpassword=${POSTGRES_PASSWORD} \
rest.jar
