## File Store
Simple service that implements CRUD operations for files.

## Initial setup & generation

### Installation guide

* Install [SDK man](https://sdkman.io/)

* Install micronaut `3.8.3` using SDK man.
```bash
sdk update
sdk install micronaut 3.8.3
```

## Packaging and Running tests

* If you just want to compile and run service
```bash
./mvnw clean package -DskipTests
```

* To run `unit` tests only
```bash
./mvnw test
```

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

* Register Debezium connector for postgresql DB and table. Check `curl` commands from `file-store/curl-tests/connector-operations.txt`

* Check Debezium properly capture DB events and publish into Kafka topic `filesync1.public.file_outbox`

Execute script (the script will block until there is message available in kafka topic):
```bash
cd infra
./kafka-client.sh
```

* Execute few API calls from `file-store` service using `curl` command from `file-store/curl-tests/api-operations.txt`

### Cleanup
Remove all docker containers using:

```bash
cd infra
./cleanup-infra.sh
```

