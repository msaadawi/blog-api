package msaadawi.blogApi.common.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageResult<T> {

    private int pageSize;

    private long totalSize;

    @Builder.Default
    private List<T> elements = new ArrayList<>();

    @JsonProperty(value = "isLastPage")
    private boolean isLastPage;
}
