package org.max.cdc.debezium.file.store.domain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@MappedEntity
@Table(name = "file_data")
@Introspected // required for native-image
@SuppressWarnings("MissingSummary")
@Schema(requiredProperties = {"id", "name"})
public class FileData {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

}
