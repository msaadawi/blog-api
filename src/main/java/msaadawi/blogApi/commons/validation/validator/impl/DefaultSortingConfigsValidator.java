package msaadawi.blogApi.commons.validation.validator.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.validation.validator.SortingConfigsValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DefaultSortingConfigsValidator implements SortingConfigsValidator {

    private final Validator validator;

    @Override
    public void doValidate(List<? extends SortingConfiguration> sortingConfigurations) {
        for (SortingConfiguration sortingConfig : sortingConfigurations) {
            Set<ConstraintViolation<SortingConfiguration>> sortingConfigViolations = validator.validate(sortingConfig);
            if (!sortingConfigViolations.isEmpty()) throw new ConstraintViolationException(sortingConfigViolations);
        }
    }
}
