package msaadawi.blogApi.common.web.paging.impl;

import lombok.*;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.validation.Validatable;
import msaadawi.blogApi.common.exception.NoSuchPropertyException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultPagingSettings implements PagingSettings, Validatable {

    @Min(1)
    private Integer pageNumber;

    @Min(1)
    @Max(100)
    private Integer pageSize;

    @Override
    public String getResourceName() {
        return "Paging Settings";
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
