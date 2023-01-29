package msaadawi.blogApi.common.error;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public interface ApiError {

    String TIMESTAMP_ATT = "timestamp";
    String STATUS_CODE_ATT = "statusCode";
    String REASON_PHRASE_ATT = "reasonPhrase";
    String DETAILS_ATT = "details";
    String ERROR_ATT = "error";

    default Date getTimestamp() {
        return Date.from(Instant.now());
    }

    int getStatusCode();

    String getReasonPhrase();

    default Object getDetails() {
        return null;
    }

    default Object buildResponse() {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put(TIMESTAMP_ATT, getTimestamp());
        responseBody.put(STATUS_CODE_ATT, getStatusCode());
        responseBody.put(REASON_PHRASE_ATT, getReasonPhrase());
        if (getDetails() != null) responseBody.put(DETAILS_ATT, getDetails());
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(ERROR_ATT, responseBody);
        return response;
    }
}
