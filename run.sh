#!/bin/bash

mvn -f ./pom.xml clean package \
  && docker build -t keycloak-phone:snapshot ./target/ \
  && docker-compose -f ./docker/docker-compose.yml down \
  && docker-compose -f ./docker/docker-compose.yml up
