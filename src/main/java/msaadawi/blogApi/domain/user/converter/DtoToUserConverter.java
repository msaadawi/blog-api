package msaadawi.blogApi.domain.user.converter;

import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import java.util.List;

public interface DtoToUserConverter {

    UserModel toUser(RequestUserDto source);

    default List<UserModel> toUserList(List<? extends RequestUserDto> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toUser).toList();
    }

    /**
     * converts a dto that is intended for update operation to a model object.
     *
     * @param source the dto carrying the update data.
     * @return an updated model to be synced with the data store.
     */
    UserModel toUpdatableUser(RequestUserDto source);

    default List<UserModel> toUpdatableUserList(List<? extends RequestUserDto> sourceList) {
        return sourceList.stream()
                .map(this::toUpdatableUser)
                .toList();
    }
}
