package msaadawi.blogApi.common.validation.validator;

import msaadawi.blogApi.common.web.sorting.SortingSettings;

import java.util.List;

public interface SortingSettingsValidator {

    void doValidate(List<? extends SortingSettings> sortingSettingsList);
}
