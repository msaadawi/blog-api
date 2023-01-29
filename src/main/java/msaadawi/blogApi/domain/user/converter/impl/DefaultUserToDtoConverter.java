package msaadawi.blogApi.domain.user.converter.impl;

import msaadawi.blogApi.domain.user.converter.UserToDtoConverter;
import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;
import msaadawi.blogApi.domain.user.web.payload.impl.DefaultResponseUserDto;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserToDtoConverter implements UserToDtoConverter {

    @Override
    public ResponseUserDto toUserDto(UserModel source) {
        if (source == null) return null;
        return DefaultResponseUserDto.builder()
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
