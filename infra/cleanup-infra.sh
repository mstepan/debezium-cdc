#!/usr/bin/env bash

export DEBEZIUM_VERSION=2.1

docker compose -f infra-compose.yaml down
