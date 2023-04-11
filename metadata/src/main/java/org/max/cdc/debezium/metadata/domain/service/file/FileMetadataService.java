package org.max.cdc.debezium.metadata.domain.service.file;

import jakarta.inject.Singleton;
import java.util.Optional;
import javax.transaction.Transactional;
import org.max.cdc.debezium.metadata.domain.model.FileMetadata;

@Singleton
public class FileMetadataService {

    private final FileMetadataRepository fileMetadataRepo;

    public FileMetadataService(FileMetadataRepository fileMetadataRepo) {
        this.fileMetadataRepo = fileMetadataRepo;
    }

    @Transactional
    public Optional<FileMetadata> getById(String fileId) {
        return fileMetadataRepo.findById(fileId);
    }
}
