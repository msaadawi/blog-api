package msaadawi.blogApi.commons.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationError {

    private String resource;

    private String attribute;

    private Object submittedValue;

    private String explanation;
}
