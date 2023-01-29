package msaadawi.blogApi.domain.comment.validation.validator.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.comment.validation.group.OnReferencedByComment;
import msaadawi.blogApi.domain.comment.validation.validator.CommentRequestPayloadValidator;
import msaadawi.blogApi.domain.comment.web.payload.CommentDto;
import msaadawi.blogApi.domain.comment.web.payload.RequestCommentDto;
import msaadawi.blogApi.common.validation.group.OnBulkUpdate;
import msaadawi.blogApi.common.validation.group.OnSingleInsert;
import msaadawi.blogApi.common.validation.group.OnSingleUpdate;
import msaadawi.blogApi.domain.user.web.payload.UserDto;
import msaadawi.blogApi.domain.post.web.payload.PostDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DefaultCommentRequestPayloadValidator implements CommentRequestPayloadValidator {

    private final Validator validator;

    @Override
    public void validatePayloadForSingleCreateOp(RequestCommentDto reqCommentDto) {
        Set<ConstraintViolation<CommentDto>> commentViolations = validator.validate(reqCommentDto, OnSingleInsert.class);
        if (!commentViolations.isEmpty()) throw new ConstraintViolationException(commentViolations);

        Set<ConstraintViolation<PostDto>> postAddedToViolations = validator.validate(reqCommentDto.getPost(),
                OnReferencedByComment.class);
        if (!postAddedToViolations.isEmpty()) throw new ConstraintViolationException(postAddedToViolations);

        Set<ConstraintViolation<UserDto>> ownerViolations = validator.validate(reqCommentDto.getOwner(),
                OnReferencedByComment.class);
        if (!ownerViolations.isEmpty()) throw new ConstraintViolationException(ownerViolations);
    }

    @Override
    public void validatePayloadForSingleUpdateOp(RequestCommentDto reqCommentDto) {
        Set<ConstraintViolation<CommentDto>> commentViolations = validator.validate(reqCommentDto, OnSingleUpdate.class);
        if (!commentViolations.isEmpty()) throw new ConstraintViolationException(commentViolations);
    }

    @Override
    public void validatePayloadForBulkUpdateOp(List<? extends RequestCommentDto> reqCommentDtos) {
        for (CommentDto commentDto : reqCommentDtos) {
            Set<ConstraintViolation<CommentDto>> violations = validator.validate(commentDto,
                    OnBulkUpdate.class);
            if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        }
    }
}
