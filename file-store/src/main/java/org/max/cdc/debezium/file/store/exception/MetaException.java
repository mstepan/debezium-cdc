package org.max.cdc.debezium.file.store.exception;

import java.io.Serial;

public class MetaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6012080713843273569L;

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
