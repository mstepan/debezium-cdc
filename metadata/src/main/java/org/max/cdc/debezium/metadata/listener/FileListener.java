package org.max.cdc.debezium.metadata.listener;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.max.cdc.debezium.metadata.domain.model.FileMetadata;
import org.max.cdc.debezium.metadata.domain.service.file.FileMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <a href="https://micronaut-projects.github.io/micronaut-kafka/4.5.0/guide/index.html">Micronaut Kafka</a>
 */
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class FileListener {

    private final ObjectMapper mapper;

    private final FileMetadataRepository metadataRepo;

    public FileListener(ObjectMapper mapper, FileMetadataRepository metadataRepo) {
        this.mapper = mapper;
        this.metadataRepo = metadataRepo;
    }

    private static final Logger LOG = LoggerFactory.getLogger(FileListener.class);

    @Topic("filesync1.file.file_outbox")
    public void receive(@KafkaKey String key, String value) {
        try {
            JsonNode node = mapper.readTree(value);

            String payloadAsText = node.get("payload").get("after").get("payload").asText();
            LOG.info("Message from Kafka '{}'", payloadAsText);

            JsonNode payloadNode = mapper.readTree(payloadAsText);

            metadataRepo.save(toFileMetadata(payloadNode));
        }
        catch (JacksonException ex) {
            LOG.error("Can't properly read message from kafka", ex);
        }
    }

    /**
     * Convert JSON node {"name": "omics/human-genome.fastq", "fileId": "446f3bcc-d1e0-4e69-221f-1401ae356444", "format": "FASTQ"}
     * to FileMetadata domain object.
     */
    private static FileMetadata toFileMetadata(JsonNode payloadNode) {
        return new FileMetadata(payloadNode.get("fileId").asText(), payloadNode.get("name").asText(),
            payloadNode.get("format").asText());
    }
}
