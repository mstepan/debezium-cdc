package org.max.cdc.debezium.file.store.api.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import org.max.cdc.debezium.file.store.domain.model.FileData;

@Introspected
@Schema(requiredProperties = {"id", "name"})
public record FileCreateRequest(

    @JsonProperty("id") String id,

    @JsonProperty("name") String name,

    @JsonProperty("format") String format
) {
    public FileData toEntity() {
        return new FileData(id, name, format);
    }
}
