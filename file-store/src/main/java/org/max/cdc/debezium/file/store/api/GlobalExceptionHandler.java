package org.max.cdc.debezium.file.store.api;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class GlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse<ErrorResponse>> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public HttpResponse<ErrorResponse> handle(HttpRequest request, Exception ex) {

        LOG.error(ex.getMessage(), ex);

        ErrorResponse response = new ErrorResponse("Internal Server Error",
            "Something wrong with the server");

        return HttpResponse.serverError(response);
    }
}
