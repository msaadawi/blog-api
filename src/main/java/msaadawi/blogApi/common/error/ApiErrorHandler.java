package msaadawi.blogApi.common.error;

import msaadawi.blogApi.common.exception.EntityAlreadyExistException;
import msaadawi.blogApi.common.exception.EntityNotFoundException;
import msaadawi.blogApi.common.validation.Validatable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApiErrorHandler {

    private static final String ERROR_CAUSE_ATT = "errorCause";
    private static final String ERROR_ATT = "error";
    private static final String ERRORS_ATT = "errors";

    private static final Logger LOG = LoggerFactory.getLogger(ApiErrorHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(ERROR_CAUSE_ATT, ex.getMessage());
        ApiError apiError = SimpleApiError
                .builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .details(errorDetails)
                .build();
        return new ResponseEntity<>(apiError.buildResponse(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<Object> handleEntityAlreadyExistException(EntityAlreadyExistException ex) {
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(ERROR_CAUSE_ATT, ex.getMessage());
        ApiError apiError = SimpleApiError
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(errorDetails)
                .build();

        return new ResponseEntity<>(apiError.buildResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ValidationError> validationErrors = ex.getConstraintViolations().stream().map(violation -> {
            Validatable validatable = getValidatedBean(violation.getRootBean());
            String attribute = violation.getPropertyPath().toString();
            return ValidationError.builder()
                    .resource(validatable.getResourceName())
                    .attribute(attribute)
                    .submittedValue(validatable.getFieldValueByName(attribute))
                    .explanation(violation.getMessage())
                    .build();
        }).toList();

        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(ERROR_CAUSE_ATT, "Some validation errors have occurred");
        errorDetails.put(ERRORS_ATT, validationErrors);
        ApiError apiError = SimpleApiError
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(errorDetails)
                .build();

        return new ResponseEntity<>(apiError.buildResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownError(Exception ex) {
        LOG.error("Api Error caused by exception: ", ex);

        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put(ERROR_CAUSE_ATT, "An unexpected error has occurred. Please try again later or contact support.");
        ApiError apiError = SimpleApiError
                .builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reasonPhrase(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .details(errorDetails)
                .build();

        return new ResponseEntity<>(apiError.buildResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Validatable getValidatedBean(Object violationRootBean) {
        if (violationRootBean == null) throw new IllegalArgumentException("violationRootBean is null");
        try {
            return (Validatable) violationRootBean;
        } catch (ClassCastException castException) {
            throw new IllegalStateException("Could not obtain a validated bean instance. " +
                    "violationRootBean should implement " + Validatable.class.getName());
        }
    }
}
