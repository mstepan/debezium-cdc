package org.max.cdc.debezium.file.store.domain.service.file;

import org.max.cdc.debezium.file.store.domain.model.FileData;
import java.util.Optional;

public interface FileDataRepository {
    FileData save(FileData entity);

    Optional<FileData> findById(String id);
}
