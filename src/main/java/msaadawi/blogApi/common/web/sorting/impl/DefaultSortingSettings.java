package msaadawi.blogApi.common.web.sorting.impl;

import lombok.*;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.validation.Validatable;
import msaadawi.blogApi.common.exception.NoSuchPropertyException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class DefaultSortingSettings implements SortingSettings, Validatable {

    @NotNull
    @Pattern(regexp = "\\s*([a-zA-Z$][\\w$]*|_[\\w$]+)\\s*")
    private String sortBy;

    @Pattern(regexp = "\\s*(asc|ascending|desc|descending)?\\s*",
            flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String sortOrder;

    @Override
    public String getResourceName() {
        return "Sorting Settings";
    }

    @Override
    public Object getFieldValueByName(String fieldName) throws NoSuchPropertyException {
        if (fieldName == null) throw new IllegalArgumentException("fieldName is null.");
        if (fieldName.equals("sortBy")) return sortBy;
        if (fieldName.equals("sortOrder")) return sortOrder;
        throw new NoSuchPropertyException("there is no field with name "
                + fieldName + " in " + getClass().getName());
    }
}
