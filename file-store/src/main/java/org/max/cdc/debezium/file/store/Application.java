package org.max.cdc.debezium.file.store;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "file-store",
        // for more details related to API versioning strategy check
        // https://gbuconfluence.oraclecorp.com/display/HCGBUDev/HCGBU+REST+API+Guidelines
        version = "1.0.0",
        description = "File Store API"
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

}
