package msaadawi.blogApi.domain.user.validator;

import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import java.util.List;

public interface UserRequestPayloadValidator {

    default void validatePayloadForSingleCreate(RequestUserDto reqUserDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForSingleUpdate(RequestUserDto reqUserDto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }

    default void validatePayloadForBulkUpdate(List<? extends RequestUserDto> reqUserDtos)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported validation type.");
    }
}
