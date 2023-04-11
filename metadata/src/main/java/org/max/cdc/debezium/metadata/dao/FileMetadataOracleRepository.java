package org.max.cdc.debezium.metadata.dao;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.micronaut.context.annotation.Primary;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import org.max.cdc.debezium.metadata.domain.model.FileMetadata;
import org.max.cdc.debezium.metadata.domain.service.file.FileMetadataRepository;

@Primary
@SuppressFBWarnings
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface FileMetadataOracleRepository extends CrudRepository<FileMetadata, String>, FileMetadataRepository {


}
