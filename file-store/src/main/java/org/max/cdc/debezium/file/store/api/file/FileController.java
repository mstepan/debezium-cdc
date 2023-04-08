package org.max.cdc.debezium.file.store.api.file;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.max.cdc.debezium.file.store.api.ErrorResponse;
import org.max.cdc.debezium.file.store.domain.model.FileData;
import org.max.cdc.debezium.file.store.domain.service.file.FileService;

@Controller("/v1/files")
@ExecuteOn(TaskExecutors.IO)
public class FileController {

    //private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final FileService fileService;
    private final String contextPath;

    @Inject
    public FileController(FileService fileService,
                          @Property(name = "micronaut.server.context-path") String contextPath) {
        this.fileService = fileService;
        this.contextPath = Objects.requireNonNull(contextPath, "null 'contextPath' detected");
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<?> create(@Body FileCreateRequest data) {

        FileData entity = data.toEntity();

        Optional<FileData> maybeMeta = fileService.create(entity);

        if (maybeMeta.isEmpty()) {
            return HttpResponse
                .badRequest(
                    new ErrorResponse("File Creation Failed",
                        "Failed to create file",
                        Map.of("id", entity.getId(),
                            "name", data.name())));

        }

        URI location = getLocation(maybeMeta.get().getId());
        return HttpResponse.created(location);
    }

    @Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> getById(@PathVariable("id") String fileId) {

        Optional<FileData> maybeFileData = fileService.getById(fileId);

        if (maybeFileData.isEmpty()) {
            return HttpResponse.notFound(
                new ErrorResponse("File not found",
                    "Can't find file by 'id'",
                    Map.of("id", fileId)));
        }

        return HttpResponse.ok(maybeFileData.get());
    }

    private URI getLocation(String id) {
        return URI.create("%s/v1/files/%s".formatted(contextPath, id));
    }

}
