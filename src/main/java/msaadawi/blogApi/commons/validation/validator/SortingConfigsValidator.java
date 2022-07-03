package msaadawi.blogApi.commons.validation.validator;

import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;

import java.util.List;

public interface SortingConfigsValidator {

    void doValidate(List<? extends SortingConfiguration> sortingConfigurations);
}
