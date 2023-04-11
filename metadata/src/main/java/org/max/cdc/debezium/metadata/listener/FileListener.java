package org.max.cdc.debezium.metadata.listener;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class FileListener {

    private static final Logger LOG = LoggerFactory.getLogger(FileListener.class);

    @Topic("filesync1.public.file_outbox")
    public void receive(@KafkaKey String key, String value) {
        LOG.info("Got File with key: {} and value: {}", key, value);
    }
}
