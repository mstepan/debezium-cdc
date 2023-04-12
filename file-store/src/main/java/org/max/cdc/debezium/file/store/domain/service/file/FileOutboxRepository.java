package org.max.cdc.debezium.file.store.domain.service.file;

import java.util.Optional;
import org.max.cdc.debezium.file.store.domain.model.FileData;
import org.max.cdc.debezium.file.store.domain.model.FileOutbox;

public interface FileOutboxRepository {

    void deleteAll();

    FileOutbox save(FileOutbox entity);

}
