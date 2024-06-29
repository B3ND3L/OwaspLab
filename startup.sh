#!/bin/bash

docker compose rm
docker rmi -f $(docker images | grep owasplab | awk '{print $3}')
docker compose build --no-cache
docker compose up -d