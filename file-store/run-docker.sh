#!/usr/bin/env bash
##############################################################################################
# Build docker locally
##############################################################################################
# 1. compile & package
# ./mvnw clean package -DskipTests
#
# 2. build docker image with any provided docker file
#
# 2.1. standard JDK image
# docker build -f Dockerfile -t mylocal/file-store:latest .
#
# 3. run docker container
# ./run-docker.sh

##############################################################################################
# Run docker
##############################################################################################

docker run --rm -p 7070:7070 -p 7071:7071 \
  --memory=4g --memory-reservation=512m \
  --name file-store \
  --network file-store \
  -e JDBC_DIALECT="POSTGRES" \
  -e JDBC_DRIVER_CLASS="org.postgresql.Driver" \
  -e JDBC_URL="jdbc:postgresql://localhost:5432/postgres?currentSchema=public" \
  -e JDBC_USER="postgres" \
  -e JDBC_PASSWORD="postgres" \
  -e JAVA_TOOL_OPTIONS="-XX:+UseParallelGC -XX:MaxRAMPercentage=75 -XX:MaxMetaspaceSize=217578K -XX:ReservedCodeCacheSize=240M -Xss1M -Xmx3638805K" \
  mylocal/file-store:latest
