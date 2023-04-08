#!/usr/bin/env bash

DB_DOCKER_NAME=file-store-postgres
NETWORK_NAME=file-store

echo "Trying to delete previous '$DB_DOCKER_NAME' container"
docker stop $DB_DOCKER_NAME || true
docker rm $DB_DOCKER_NAME || true
docker network rm ${NETWORK_NAME} || true

echo "Creating docker network '$NETWORK_NAME'"
docker network create -d bridge ${NETWORK_NAME} || exit 1

echo "Creating new DB container '$DB_DOCKER_NAME'"

#
# https://hub.docker.com/_/postgres
#
docker run --name $DB_DOCKER_NAME -d \
  -p 5432:5432 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  --network ${NETWORK_NAME} \
  postgres:15 || exit 1

