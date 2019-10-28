FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/udemy

# ADD .jar under the target location from host
# into this image
ADD target/selenium-docker.jar 			selenium-docker.jar
ADD target/selenium-docker-tests.jar 	selenium-docker-tests.jar
ADD target/libs							libs
#Note: If we have any other files like .json/ .xls
#we need to add them too.

# ADD suite files
ADD flightTestNG.xml 		flightTestNG.xml
ADD searchTestNG.xml		searchTestNG.xml

#ADD health check script
ADD healthcheck.sh			healthCheck.sh

# System parameters are:
# BROWSER
# HUB_PORT
# XMLFILE
ENTRYPOINT sh healthCheck.sh