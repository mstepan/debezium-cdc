## Transactional messaging

Fully working solution for transactional messaging pattern using Debezium CDC.

* File Store service [file-store](file-store/README.md)
* Metadata service [metadata](...)
* Debezium CDC [Debezium CDC](...)

## Running locally

* Build `file-store` & `metadata` and create local docker images
```bash
cd infra
./build-all.sh
```

* Start all Debezium related containers:
```bash
cd infra
./run-infra.sh
````
This will spin up Postgresql DB, Kafka, Zookeeper and Debezium connector using docker compose file from `infra-compose.yaml`

* Create postgresql schema called `metadata`
For details check `metadata/db/create-db-schema.sh`

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

### Troubleshooting

Check `metadata` container logs

```bash
docker logs -f infra-metadata-1
```

Check `file-store` container logs

```bash
docker logs -f infra-file-store-1
```