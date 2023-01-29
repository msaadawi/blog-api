package msaadawi.blogApi.domain.post.validation.validator;

import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;

import java.util.List;

public interface PostRequestPayloadValidator {

    default void validatePayloadForSingleCreateOp(RequestPostDto reqPostDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForSingleUpdateOp(RequestPostDto reqPostDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForBulkUpdateOp(List<? extends RequestPostDto> requestPostDtos)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }
}
