package msaadawi.blogApi.commons.error;


import msaadawi.blogApi.commons.exception.EntityAlreadyExistException;
import msaadawi.blogApi.commons.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class ApiErrorHandler {

    private static final String SHORT_ERROR_CAUSE = "errorCause";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(SHORT_ERROR_CAUSE, ex.getMessage());

        ApiError apiError = ApiError.builder()
                .timestamp(Date.from(Instant.now()))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .details(errorDetails)
                .build();

        return buildFinalErrorResponse(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<Object> handleEntityAlreadyExistException(EntityAlreadyExistException ex) {
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(SHORT_ERROR_CAUSE, ex.getMessage());

        ApiError apiError = ApiError.builder()
                .timestamp(Date.from(Instant.now()))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(errorDetails)
                .build();

        return buildFinalErrorResponse(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ValidationError> validationErrors = ex.getConstraintViolations().stream().map(violation -> {
            ValidatedBean validatedBean = getValidatedBean(violation.getRootBean());
            String attribute = violation.getPropertyPath().toString();
            return ValidationError.builder()
                    .resource(validatedBean.getResourceName())
                    .attribute(attribute)
                    .submittedValue(validatedBean.getFieldValueByName(attribute))
                    .explanation(violation.getMessage())
                    .build();
        }).toList();

        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(SHORT_ERROR_CAUSE, "Some validation errors have occurred");
        errorDetails.put("validationErrors", validationErrors);

        ApiError apiError = ApiError.builder()
                .timestamp(Date.from(Instant.now()))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(errorDetails)
                .build();

        return buildFinalErrorResponse(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownError(Exception ex) {
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(SHORT_ERROR_CAUSE, "An unexpected error has occurred. Please try again later or contact support.");

        ApiError apiError = ApiError.builder()
                .timestamp(Date.from(Instant.now()))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reasonPhrase(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .details(errorDetails)
                .build();

        return buildFinalErrorResponse(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ValidatedBean getValidatedBean(Object violationRootBean) {
        if (violationRootBean == null) throw new IllegalArgumentException("violationRootBean is null");
        try {
            return (ValidatedBean) violationRootBean;
        } catch (ClassCastException castException) {
            throw new IllegalStateException("Could not obtain a validated bean instance. " +
                    "violationRootBean should implement " + ValidatedBean.class.getName());
        }
    }

    private ResponseEntity<Object> buildFinalErrorResponse(ApiError apiError, HttpStatus status) {
        return new ResponseEntity<>(Map.of("error", apiError), status);
    }
}
