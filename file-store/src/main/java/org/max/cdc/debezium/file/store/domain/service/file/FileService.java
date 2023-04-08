package org.max.cdc.debezium.file.store.domain.service.file;

import jakarta.inject.Singleton;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.max.cdc.debezium.file.store.domain.model.FileData;
import org.max.cdc.debezium.file.store.domain.model.FileOutbox;

@Singleton
public class FileService {

    private final FileDataRepository fileDataRepo;

    private final FileOutboxRepository outboxRepo;

    public FileService(FileDataRepository fileDataRepo, FileOutboxRepository outboxRepo) {
        this.fileDataRepo = fileDataRepo;
        this.outboxRepo = outboxRepo;
    }

    @Transactional
    public Optional<FileData> create(FileData fileData) {
        Objects.requireNonNull(fileData, "null 'fileData' detected");
        Optional<FileData> maybeFileData = Optional.of(fileDataRepo.save(fileData));

        outboxRepo.save(new FileOutbox(fileData.getId(), "UPLOADED", "<some payload>"));

        return maybeFileData;
    }

    @Transactional
    public Optional<FileData> getById(String fileId) {
        return fileDataRepo.findById(fileId);
    }
}
