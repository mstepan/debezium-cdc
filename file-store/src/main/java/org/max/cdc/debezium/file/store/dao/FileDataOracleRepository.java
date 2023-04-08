package org.max.cdc.debezium.file.store.dao;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.micronaut.context.annotation.Primary;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import org.max.cdc.debezium.file.store.domain.model.FileData;
import org.max.cdc.debezium.file.store.domain.service.file.FileDataRepository;

@Primary
@SuppressFBWarnings
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface FileDataOracleRepository extends CrudRepository<FileData, String>, FileDataRepository {


}
