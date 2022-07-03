package msaadawi.blogApi.commons.validation.validator.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.validation.validator.PagingConfigValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DefaultPagingConfigValidator implements PagingConfigValidator {

    private final Validator validator;

    @Override
    public void doValidate(PagingConfiguration pagingConfiguration) {
        Set<ConstraintViolation<PagingConfiguration>> pagingConfigViolations = validator.validate(pagingConfiguration);
        if (!pagingConfigViolations.isEmpty())
            throw new ConstraintViolationException(pagingConfigViolations);
    }
}
