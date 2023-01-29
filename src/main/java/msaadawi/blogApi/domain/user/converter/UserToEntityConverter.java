package msaadawi.blogApi.domain.user.converter;

import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.data.entity.UserEntity;

import java.util.List;

public interface UserToEntityConverter {

    UserEntity toUserEntity(UserModel source);

    default List<UserEntity> toUserEntityList(List<? extends UserModel> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toUserEntity).toList();
    }
}
