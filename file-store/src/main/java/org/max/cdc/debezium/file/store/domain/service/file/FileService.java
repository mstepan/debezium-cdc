package org.max.cdc.debezium.file.store.domain.service.file;

import jakarta.inject.Singleton;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.max.cdc.debezium.file.store.domain.model.FileData;

@Singleton
public class FileService {

    private final FileDataRepository repository;

    public FileService(FileDataRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<FileData> create(FileData fileData) {
        Objects.requireNonNull(fileData, "null 'fileData' detected");
        return Optional.of(repository.save(fileData));
    }

    @Transactional
    public Optional<FileData> getById(String fileId) {
        return repository.findById(fileId);
    }
}
