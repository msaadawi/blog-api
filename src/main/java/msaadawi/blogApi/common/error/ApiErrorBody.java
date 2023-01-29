package msaadawi.blogApi.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiErrorBody {

    private Date timestamp;

    private int statusCode;

    private String reasonPhrase;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object details;
}
