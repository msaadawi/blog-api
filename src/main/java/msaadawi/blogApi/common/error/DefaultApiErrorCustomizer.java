package msaadawi.blogApi.common.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultApiErrorCustomizer extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> defaultAtts = super.getErrorAttributes(webRequest, options);
        Map<String, Object> customAtts = new LinkedHashMap<>();
        customAtts.put(ApiError.TIMESTAMP_ATT, defaultAtts.get("timestamp"));
        customAtts.put(ApiError.STATUS_CODE_ATT, defaultAtts.get("status"));
        customAtts.put(ApiError.REASON_PHRASE_ATT, defaultAtts.get("error"));
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(ApiError.ERROR_ATT, customAtts);
        return response;
    }
}
