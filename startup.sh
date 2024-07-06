#!/bin/bash

cd api
mvn install

cd -
docker compose rm -f
docker rmi -f $(docker images | grep owasplab | awk '{print $3}')
docker compose build --no-cache
docker compose up -d