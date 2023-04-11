#!/usr/bin/env bash

# Postgresql
export JDBC_DIALECT="POSTGRES"
export JDBC_DRIVER_CLASS="org.postgresql.Driver"
export JDBC_URL="jdbc:postgresql://localhost:5432/postgres?currentSchema=metadata"
export JDBC_USER="postgres"
export JDBC_PASSWORD="postgres"

#export REMOTE_DEBUGGER=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4000
export JAVA_TOOL_OPTIONS="-XX:+UseParallelGC -XX:MaxRAMPercentage=75 -XX:MaxMetaspaceSize=217578K -XX:ReservedCodeCacheSize=240M -Xss1M -Xmx3638805K"

java ${REMOTE_DEBUGGER} -jar target/$(basename metadata-*.jar)
