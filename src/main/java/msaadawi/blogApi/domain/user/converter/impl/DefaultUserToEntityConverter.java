package msaadawi.blogApi.domain.user.converter.impl;

import msaadawi.blogApi.domain.user.converter.UserToEntityConverter;
import msaadawi.blogApi.domain.user.data.entity.UserEntity;
import msaadawi.blogApi.domain.user.model.UserModel;
import org.springframework.stereotype.Component;


@Component
public class DefaultUserToEntityConverter implements UserToEntityConverter {

    @Override
    public UserEntity toUserEntity(UserModel source) {
        if (source == null) return null;
        return UserEntity.builder()
                .id(source.getId())
                .username(source.getUsername())
                .email(source.getEmail())
                .phone(source.getPhone())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .birthDate(source.getBirthDate())
                .profession(source.getProfession())
                .currentLocation(source.getCurrentLocation())
                .build();
    }
}
