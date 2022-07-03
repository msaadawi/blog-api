package msaadawi.blogApi.domain.user.converter.impl;

import msaadawi.blogApi.domain.user.converter.EntityToUserConverter;
import msaadawi.blogApi.domain.user.model.impl.DefaultUserModel;
import msaadawi.blogApi.domain.user.data.entity.UserEntity;
import msaadawi.blogApi.domain.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class DefaultEntityToUserConverter implements EntityToUserConverter {

    @Override
    public UserModel toUser(UserEntity source) {
        if (source == null) return null;
        return DefaultUserModel.builder()
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
