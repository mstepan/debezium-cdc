#!/bin/bash

#
# To run this script you need to install:
# 1. Golang. https://go.dev/doc/install
# 2. java-buildpack-memory-calculator. https://github.com/cloudfoundry/java-buildpack-memory-calculator
#

# we expect POD RAM to be at least 4 GB
TOTAL_POD_MEMORY=4G

# loaded classes count:
# curl -v -X GET $ADMIN_URL/metadata/metrics/jvm.classes.loaded | jq ==> "value": 11733
# we have ~12K of classes, so I just multiplied by 3 to be safe, so we have 36K classes as an assumption
LOADED_CLASSES_COUNT=36000

# total thread pool size
# curl -v -X GET $ADMIN_URL/metadata/metrics/jvm.threads.live| jq ==> "value": 78
# so we add few additional threads for overhead
THREADS_COUNT=90

#
# we expect to use parallel GC and 75% of memory
# for more details check https://docs.microsoft.com/en-us/azure/developer/java/containers/overview
#
# Here -XX:MaxDirectMemorySize=0 is the default JVM value that means that we have direct memory size equals
# to total heap size.
#
JVM_OPTIONS="-XX:+UseParallelGC -XX:MaxRAMPercentage=75 -XX:MaxDirectMemorySize=0"

java-buildpack-memory-calculator \
  --total-memory ${TOTAL_POD_MEMORY} \
  --loaded-class-count ${LOADED_CLASSES_COUNT} \
  --thread-count ${THREADS_COUNT} \
  --jvm-options "${JVM_OPTIONS}"
