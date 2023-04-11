#!/usr/bin/env bash

# Create Postgresql DB schema

docker exec -it infra-postgres-1 psql -U postgres

CREATE SCHEMA metadata;