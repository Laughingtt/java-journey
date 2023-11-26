#/bin/bash
export APP_NAME=microservice-dataprovider

#if [ ! -f "conf/application.yml" ]; then
#    cp application.yml conf/
#fi
#if [ ! -f "conf/logback-spring.xml" ]; then
#    cp logback-spring.xml conf/
#    exit 0
#fi

java \
  -Duser.timezone=GMT+8 \
  -Dspring.config.location=file:conf/application.yml \
  -Dlogging.config=file:conf/logback-spring.xml \
  -jar \
  -Xmx2G \
  -Xms512m \
  ${APP_NAME}.jar
