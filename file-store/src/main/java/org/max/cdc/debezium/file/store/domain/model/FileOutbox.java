package org.max.cdc.debezium.file.store.domain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.model.DataType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@MappedEntity
@Table(name = "file_outbox")
@Introspected
@SuppressWarnings("MissingSummary")
@Schema(requiredProperties = {"fileId", "type", "payload"})
public class FileOutbox {

    @Id
    @Column(name = "file_id")
    private String fileId;

    @Column(name = "type")
    private String type;

    @Column(name = "payload")
    @TypeDef(type = DataType.JSON)
    private Map<String, Object> payload;

}
