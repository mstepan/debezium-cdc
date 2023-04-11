package org.max.cdc.debezium.metadata.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import java.util.Map;
import java.util.Objects;

/**
 * We suggest to use <a href="https://www.rfc-editor.org/rfc/rfc7807">RFC-7807</a> as a standard for error messages.
 *
 * @param title  - A short, human-readable summary of the problem type.
 * @param detail - A human-readable explanation specific to this occurrence of the problem.
 * @param info   - Additional information that will help describe problem better.
 */
@Introspected // required for native-image
public record ErrorResponse(@JsonProperty("title") String title,
                            @JsonProperty("detail") String detail,
                            @JsonProperty("info") Map<String, String> info) {

    public ErrorResponse {
        Objects.requireNonNull(title, "null 'title' detected");
        info = (info == null) ? Map.of() : Map.copyOf(info);
    }

    public ErrorResponse(String title) {
        this(title, null, Map.of());
    }

    public ErrorResponse(String title, String detail) {
        this(title, detail, Map.of());
    }

    @Override
    public Map<String, String> info() {
        return Map.copyOf(info);
    }
}
