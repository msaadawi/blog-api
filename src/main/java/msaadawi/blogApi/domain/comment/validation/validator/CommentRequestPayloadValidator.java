package msaadawi.blogApi.domain.comment.validation.validator;

import msaadawi.blogApi.domain.comment.web.payload.RequestCommentDto;

import java.util.List;

public interface CommentRequestPayloadValidator {

    default void validatePayloadForSingleCreate(RequestCommentDto reqCommentDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForSingleUpdate(RequestCommentDto reqCommentDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForBulkUpdate(List<? extends RequestCommentDto> reqCommentDtos)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }
}
