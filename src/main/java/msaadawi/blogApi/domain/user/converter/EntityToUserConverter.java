package msaadawi.blogApi.domain.user.converter;

import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.data.entity.UserEntity;

import java.util.List;

public interface EntityToUserConverter {

    UserModel toUser(UserEntity source);

    default List<UserModel> toUserList(List<UserEntity> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toUser).toList();
    }
}
