package msaadawi.blogApi.domain.comment.validation.validator;

import msaadawi.blogApi.domain.comment.web.payload.RequestCommentDto;

import java.util.List;

public interface CommentRequestPayloadValidator {

    default void validatePayloadForSingleCreateOp(RequestCommentDto reqCommentDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForSingleUpdateOp(RequestCommentDto reqCommentDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForBulkUpdateOp(List<? extends RequestCommentDto> reqCommentDtos)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }
}
