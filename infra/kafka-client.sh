#!/usr/bin/env bash

#
# Get bootstrap server name
#

export BOOTSTRAP_SERVER=$(echo | docker exec infra-kafka-1 bash -c "cat /kafka/config/server.properties | grep advertised.listeners" | awk -F"://" '{ print $2}')
export TOPIC="filesync1.public.file_outbox"
#
# list all topics
#
#docker exec infra-kafka-1 bash -c "/kafka/bin/kafka-topics.sh --list --bootstrap-server $BOOTSTRAP_SERVER"


#
# Read last message from topic (block waiting until available)
#
echo "Waiting for messages on topic $TOPIC..."
docker exec infra-kafka-1 bash -c "bin/kafka-console-consumer.sh --bootstrap-server $BOOTSTRAP_SERVER --topic $TOPIC --max-messages 1" | jq
