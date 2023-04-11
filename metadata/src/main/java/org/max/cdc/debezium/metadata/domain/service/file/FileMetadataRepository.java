package org.max.cdc.debezium.metadata.domain.service.file;

import org.max.cdc.debezium.metadata.domain.model.FileMetadata;
import java.util.Optional;

public interface FileMetadataRepository {

    Optional<FileMetadata> findById(String id);
}
