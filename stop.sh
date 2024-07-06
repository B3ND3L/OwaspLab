#!/bin/bash

docker compose down
docker compose rm -f
docker rmi -f $(docker images | grep owasplab | awk '{print $3}')