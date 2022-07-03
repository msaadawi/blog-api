package msaadawi.blogApi.commons.config.paging.impl;

import lombok.*;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.error.ValidatedBean;
import msaadawi.blogApi.commons.exception.NoSuchPropertyException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultPagingConfiguration implements PagingConfiguration, ValidatedBean {

    @Min(1)
    private Integer pageNumber;

    @Min(1)
    @Max(100)
    private Integer pageSize;

    @Override
    public String getResourceName() {
        return "Paging Configuration";
    }

    @Override
    public Object getFieldValueByName(String fieldName) throws NoSuchPropertyException {
        if (fieldName == null) throw new IllegalArgumentException("fieldName is null.");
        if (fieldName.equals("pageSize")) return pageSize;
        if (fieldName.equals("pageNumber")) return pageNumber;
        throw new NoSuchPropertyException("there is no field with name "
                + fieldName + " in " + getClass().getName());
    }
}
