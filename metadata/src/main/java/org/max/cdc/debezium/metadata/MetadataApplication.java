package org.max.cdc.debezium.metadata;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "metadata",
        // for more details related to API versioning strategy check
        // https://gbuconfluence.oraclecorp.com/display/HCGBUDev/HCGBU+REST+API+Guidelines
        version = "1.0.0",
        description = "Metadata API"
    )
)
public class MetadataApplication {

    public static void main(String[] args) {
        Micronaut.run(MetadataApplication.class, args);
    }

}
