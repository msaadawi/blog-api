package msaadawi.blogApi.domain.user.validator;

import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import java.util.List;

public interface UserRequestPayloadValidator {

    default void validatePayloadForSingleCreateOp(RequestUserDto reqUserDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForSingleUpdateOp(RequestUserDto reqUserDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForBulkUpdateOp(List<? extends RequestUserDto> reqUserDtos)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }
}
