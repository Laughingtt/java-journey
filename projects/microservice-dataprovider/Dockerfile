FROM openjdk:8

LABEL MAINTAINER devops <devops@tianjian.com>

ARG APP_NAME=microservice-dataprovider

RUN mkdir -p /data/${APP_NAME} && \
  mkdir -p /data/${APP_NAME}/conf && \
  mkdir -p /data/${APP_NAME}/logs && \
  mkdir -p /data/${APP_NAME}/data

WORKDIR /data/${APP_NAME}

ARG 0.1.0

COPY docker/entrypoint.sh entrypoint.sh
COPY target/classes/application.yml conf/application.yml
COPY target/classes/logback-spring.xml conf/logback-spring.xml
COPY target/${APP_NAME}-${version}.jar ${APP_NAME}-${version}.jar

RUN chmod +x entrypoint.sh && \
  ln -s ${APP_NAME}-${version}.jar ${APP_NAME}.jar

ENTRYPOINT bash entrypoint.sh

#EXPOSE 8082