#!/usr/bin/env bash

# Switching to file-store folder
cd ../file-store

# Building file-store service
mvnw clean package -DskipTests || exit 1

# File-store create docker image
docker build -f Dockerfile -t mylocal/file-store:1.0.0 . || exit 1

# Switching to metadata folder
cd ../metadata

# Build metadata service
mvnw clean package -DskipTests || exit 1

# Metadata create docker image
docker build -f Dockerfile -t mylocal/metadata:1.0.0 . || exit 1

# Return back to 'infra' folder
cd ../infra
