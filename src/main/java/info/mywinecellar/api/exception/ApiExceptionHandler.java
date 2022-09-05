/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler {

    private final ObjectMapper mapper;

    /**
     * Global Exception Handler
     *
     * @param exception {@link ApiException}
     * @return the response
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(final ApiException exception) {
        HttpStatus httpStatus = exception.getHttpStatus();

        String errorJson;
        try {
            ApiError response = ApiError.builder()
                    .httpStatus(httpStatus)
                    .httpCode(httpStatus.value())
                    .message(exception.getMessage())
                    .timestamp(LocalDateTime.now())
                    .eventId(UUID.randomUUID().toString())
                    .build();
            errorJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            log.error(errorJson, exception);

        } catch (JsonProcessingException e) {
            errorJson = "JsonProcessingException: " + e.getLocalizedMessage();
        }

        return new ResponseEntity<>(errorJson, HttpStatus.valueOf(httpStatus.value()));
    }
}
