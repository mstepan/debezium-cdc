#!/usr/bin/env bash

cd ../metadata

# Build metadata service
mvnw clean package -DskipTests

# Metadata create docker image
docker build -f Dockerfile -t mylocal/metadata:1.0.0 .

# Return back to 'infra' folder
cd ../infra
