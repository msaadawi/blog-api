package msaadawi.blogApi.common.validation.validator;

import msaadawi.blogApi.common.web.paging.PagingSettings;

public interface PagingSettingsValidator {

    void doValidate(PagingSettings pagingSettings);
}
