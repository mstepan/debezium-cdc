#!/usr/bin/env bash

# Postgresql
export JDBC_DIALECT="POSTGRES"
export JDBC_DRIVER_CLASS="org.postgresql.Driver"
export JDBC_URL="jdbc:postgresql://localhost:5432/postgres?currentSchema=file"
export JDBC_USER="postgres"
export JDBC_PASSWORD="postgres"

#
# H2 with Postgresql compatibility (http://www.h2database.com/html/features.html#compatibility)
#
#export JDBC_DIALECT="H2"
#export JDBC_DRIVER_CLASS="org.h2.Driver"
#export JDBC_URL="jdbc:h2:mem:devDb;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH"
#export JDBC_USER="sa"
#export JDBC_PASSWORD=""

#export REMOTE_DEBUGGER=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=4000
export JAVA_TOOL_OPTIONS="-XX:+UseParallelGC -XX:MaxRAMPercentage=75 -XX:MaxMetaspaceSize=217578K -XX:ReservedCodeCacheSize=240M -Xss1M -Xmx3638805K"

java ${REMOTE_DEBUGGER} -jar target/$(basename file-store-*.jar)
