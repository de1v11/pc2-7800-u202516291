package upc.edu.pe.shared.interfaces.rest;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import upc.edu.pe.logistics.domain.exceptions.DeliveryOrderAlreadyExistsException;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Handles MethodArgumentNotValidException returning a localized 400 problem response.
     *
     * @param exception the validation exception to handle
     * @param locale    locale to use for error messages
     * @return the localized error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(MethodArgumentNotValidException exception, Locale locale) {
        String prefix = messageSource.getMessage("errors.found", null, locale);
        String fields = exception.getFieldErrors().stream()
                .map(fieldError -> messageSource.getMessage(fieldError, locale))
                .collect(Collectors.joining(", "));
        return ErrorResponse.create(
                exception,
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
                prefix + " " + fields);
    }

    /**
     * Handles IllegalArgumentException returning a localized 400 problem response.
     * The exception message carries the message key to resolve.
     *
     * @param exception the illegal argument exception to handle
     * @param locale    locale to use for error messages
     * @return the localized error response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(IllegalArgumentException exception, Locale locale) {
        String messageKey = exception.getMessage() != null ? exception.getMessage() : "errors.found";
        String message = Objects.requireNonNullElse(
                messageSource.getMessage(messageKey, null, messageKey, locale), messageKey);
        return ErrorResponse.create(exception, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), message);
    }

    /**
     * Handles ClaimIdentifierAlreadyExistsException returning a localized 409 problem response.
     *
     * @param exception the duplicate claim exception to handle
     * @param locale   locale to use for error messages
     * @return the localized error response
     */
    @ExceptionHandler(DeliveryOrderAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse handleException(DeliveryOrderAlreadyExistsException exception, Locale locale) {
        String messageKey = exception.getMessage();
        String message = Objects.requireNonNullElse(
                messageSource.getMessage(messageKey, null, messageKey, locale), messageKey);
        return ErrorResponse.create(exception, HttpStatusCode.valueOf(HttpStatus.CONFLICT.value()), message);
    }
}
