package msaadawi.blogApi.common.error;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SimpleApiError implements ApiError {

    private int statusCode;

    private String reasonPhrase;

    Object details;

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public Object getDetails() {
        return details;
    }
}
