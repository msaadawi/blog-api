package msaadawi.blogApi.common.validation.validator.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.validation.validator.PagingSettingsValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DefaultPagingSettingsValidator implements PagingSettingsValidator {

    private final Validator validator;

    @Override
    public void doValidate(PagingSettings pagingSettings) {
        Set<ConstraintViolation<PagingSettings>> violations = validator.validate(pagingSettings);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
