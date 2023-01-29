package msaadawi.blogApi.domain.user.validator.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.common.validation.group.OnBulkUpdate;
import msaadawi.blogApi.common.validation.group.OnSingleInsert;
import msaadawi.blogApi.common.validation.group.OnSingleUpdate;
import msaadawi.blogApi.domain.user.validator.UserRequestPayloadValidator;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;
import msaadawi.blogApi.domain.user.web.payload.UserDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DefaultUserRequestPayloadValidator implements UserRequestPayloadValidator {

    private final Validator validator;

    @Override
    public void validatePayloadForSingleCreateOp(RequestUserDto reqUserDto) {
        Set<ConstraintViolation<UserDto>> violations = validator.validate(reqUserDto, OnSingleInsert.class);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    @Override
    public void validatePayloadForSingleUpdateOp(RequestUserDto reqUserDto) {
        Set<ConstraintViolation<UserDto>> violations = validator.validate(reqUserDto, OnSingleUpdate.class);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    @Override
    public void validatePayloadForBulkUpdateOp(List<? extends RequestUserDto> reqUserDtos) {
        for (UserDto reqUserDto : reqUserDtos) {
            Set<ConstraintViolation<UserDto>> violations = validator.validate(reqUserDto,
                    OnBulkUpdate.class);
            if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        }
    }
}
