package org.max.cdc.debezium.metadata.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import java.util.Map;
import java.util.Optional;
import org.max.cdc.debezium.metadata.domain.model.FileMetadata;
import org.max.cdc.debezium.metadata.domain.service.file.FileMetadataService;

@Controller("/v1/files")
@ExecuteOn(TaskExecutors.IO)
public class MetadataController {

    private final FileMetadataService fileMetadataService;

    @Inject
    public MetadataController(FileMetadataService fileMetadataService) {
        this.fileMetadataService = fileMetadataService;
    }

    @Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> getById(@PathVariable("id") String fileId) {

        Optional<FileMetadata> maybeFileData = fileMetadataService.getById(fileId);

        if (maybeFileData.isEmpty()) {
            return HttpResponse.notFound(new ErrorResponse("FILE metadata not found",
                "Can't find FILE metadata by 'id'", Map.of("id", fileId)));
        }

        return HttpResponse.ok(maybeFileData.get());
    }

}
