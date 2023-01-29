package msaadawi.blogApi.common.validation.validator.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.validation.validator.SortingSettingsValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DefaultSortingSettingsValidator implements SortingSettingsValidator {

    private final Validator validator;

    @Override
    public void doValidate(List<? extends SortingSettings> sortingSettingsList) {
        for (SortingSettings sortingSettings : sortingSettingsList) {
            Set<ConstraintViolation<SortingSettings>> violations = validator.validate(sortingSettings);
            if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        }
    }
}
