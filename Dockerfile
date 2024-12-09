FROM eclipse-temurin:23-jdk AS compiler
# can be AS builder also

ARG COMPILE_DIR=/code_folder

WORKDIR ${COMPILE_DIR}

COPY src src
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn

RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true

# technically first stage doesn't need the ENV, EXPOSE and the ENTRYPOINT (Will not have an error)
# provided that there is a second stage
ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar target/vttp5a-ssf-day19ws-0.0.1-SNAPSHOT.jar 
# Another way to write entrypoint
# ENTRYPOINT [ "java", "-jar", "target/vttp5a-ssf-day17l-0.0.1-SNAPSHOT.jar" ]

# Stage 3
FROM eclipse-temurin:23-jdk

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY --from=compiler /code_folder/target/vttp5a-ssf-day19ws-0.0.1-SNAPSHOT.jar vttp5a-day19ws.jar

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

ENTRYPOINT [ "java", "-jar", "vttp5a-day19ws.jar" ]