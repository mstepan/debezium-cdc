package org.max.cdc.debezium.file.store.domain.service.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.max.cdc.debezium.file.store.domain.model.FileData;
import org.max.cdc.debezium.file.store.domain.model.FileOutbox;

@Singleton
public class FileService {

    private final FileDataRepository fileDataRepo;

    private final FileOutboxRepository outboxRepo;

    private final ObjectMapper objectMapper;

    public FileService(FileDataRepository fileDataRepo, FileOutboxRepository outboxRepo, ObjectMapper objectMapper) {
        this.fileDataRepo = fileDataRepo;
        this.outboxRepo = outboxRepo;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Optional<FileData> create(FileData fileData) {
        Objects.requireNonNull(fileData, "null 'fileData' detected");
        Optional<FileData> maybeFileData = Optional.of(fileDataRepo.save(fileData));

        String jsonStr = "{ \"fileId\" : \"%s\", \"name\" : \"%s\", \"format\" : \"%s\" }".
            formatted(fileData.getId(), fileData.getName(), fileData.getFormat());

        outboxRepo.save(new FileOutbox(fileData.getId(), "UPLOADED", toJson(jsonStr)));

        return maybeFileData;
    }

    private Map<String, Object> toJson(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, new TypeReference<>() {
            });
        }
        catch (JsonProcessingException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Transactional
    public Optional<FileData> getById(String fileId) {
        return fileDataRepo.findById(fileId);
    }
}
