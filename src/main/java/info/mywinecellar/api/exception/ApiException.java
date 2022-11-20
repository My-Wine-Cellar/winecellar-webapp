/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    /**
     * @param cause      the throwable cause
     * @param httpStatus {@link HttpStatus} the status of the exception
     * @param message    the message to be displayed
     */
    public ApiException(Throwable cause, HttpStatus httpStatus, String message) {
        super(cause);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    /**
     * @param httpStatus {@link HttpStatus} the status of the exception
     * @param message    the message to be displayed
     */
    public ApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
