package msaadawi.blogApi.domain.post.validation.validator.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.commons.validation.group.OnBulkUpdate;
import msaadawi.blogApi.commons.validation.group.OnSingleInsert;
import msaadawi.blogApi.commons.validation.group.OnSingleUpdate;
import msaadawi.blogApi.domain.post.validation.validator.PostRequestPayloadValidator;
import msaadawi.blogApi.domain.user.web.payload.UserDto;
import msaadawi.blogApi.domain.post.validation.group.OnReferencedByPost;
import msaadawi.blogApi.domain.post.web.payload.PostDto;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DefaultPostRequestPayloadValidator implements PostRequestPayloadValidator {

    private final Validator validator;

    @Override
    public void validatePayloadForSingleCreate(RequestPostDto reqPostDto) {
        Set<ConstraintViolation<PostDto>> postViolations = validator.validate(reqPostDto, OnSingleInsert.class);
        if (!postViolations.isEmpty()) throw new ConstraintViolationException(postViolations);

        Set<ConstraintViolation<UserDto>> ownerViolations = validator.validate(reqPostDto.getOwner(),
                OnReferencedByPost.class);
        if (!ownerViolations.isEmpty()) throw new ConstraintViolationException(ownerViolations);
    }

    @Override
    public void validatePayloadForSingleUpdate(RequestPostDto reqPostDto) {
        Set<ConstraintViolation<PostDto>> postViolations = validator.validate(reqPostDto, OnSingleUpdate.class);
        if (!postViolations.isEmpty()) throw new ConstraintViolationException(postViolations);
    }

    @Override
    public void validatePayloadForBulkUpdate(List<? extends RequestPostDto> requestPostDtos) {
        for (PostDto reqPostDto : requestPostDtos) {
            Set<ConstraintViolation<PostDto>> violations = validator.validate(reqPostDto,
                    OnBulkUpdate.class);
            if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        }
    }
}
