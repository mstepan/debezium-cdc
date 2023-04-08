package org.max.cdc.debezium.file.store.dao;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.micronaut.context.annotation.Primary;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import org.max.cdc.debezium.file.store.domain.model.FileData;
import org.max.cdc.debezium.file.store.domain.model.FileOutbox;
import org.max.cdc.debezium.file.store.domain.service.file.FileDataRepository;
import org.max.cdc.debezium.file.store.domain.service.file.FileOutboxRepository;

@Primary
@SuppressFBWarnings
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface FileOutboxOracleRepository extends CrudRepository<FileOutbox, String>, FileOutboxRepository {
}
