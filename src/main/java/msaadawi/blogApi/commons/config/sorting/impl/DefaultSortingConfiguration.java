package msaadawi.blogApi.commons.config.sorting.impl;

import lombok.*;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.error.ValidatedBean;
import msaadawi.blogApi.commons.exception.NoSuchPropertyException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class DefaultSortingConfiguration implements SortingConfiguration, ValidatedBean {

    @NotNull
    @Pattern(regexp = "\\s*([a-zA-Z$][\\w$]*|_[\\w$]+)\\s*")
    private String sortBy;

    @Pattern(regexp = "\\s*(asc|ascending|desc|descending)?\\s*",
            flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String sortOrder;

    @Override
    public String getResourceName() {
        return "Sorting Configuration";
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
