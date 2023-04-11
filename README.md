## Transactional messaging

Fully working solution for transactional messaging pattern using Debezium CDC.

* File Store service [file-store](file-store/README.md)
* Metadata service [metadata](...)
* Debezium CDC [Debezium CDC](...)

## Running locally

* Start all Debezium related containers:
```bash
cd infra
./run-infra.sh
````
This will spin up Postgresql DB, Kafka, Zookeeper and Debezium connector using docker compose file from `infra-compose.yaml`

* Start `file-store` service that will generate DB schema using liquibase
```
cd file-store
./run.sh
```

* Create postgresql schema called `metadata`
For details check `metadata/db/create-db-schema.sh`

* Start `metadata` service. Metadata service will generate all required tables inside `metadata` postgersql schema.
```bash
cd metadata
./run.sh
```

* Register Debezium connector for postgresql DB and table. Check `curl` commands from `file-store/curl-tests/connector-operations.txt`

* Check Debezium properly capture DB events and publish into Kafka topic `filesync1.public.file_outbox`

Execute script (the script will block until there is message available in kafka topic):
```bash
cd infra
./kafka-client.sh
```

* Execute few API calls from `file-store` service using `curl` command from `file-store/curl-tests/api-operations.txt`
* Execute few API calls from `metadata` service using `curl` command from `metadata/curl-tests/api-operations.txt`

### Cleanup
Remove all docker containers using:

```bash
cd infra
./cleanup-infra.sh
```