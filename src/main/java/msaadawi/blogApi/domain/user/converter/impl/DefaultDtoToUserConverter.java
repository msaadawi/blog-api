package msaadawi.blogApi.domain.user.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.user.converter.DtoToUserConverter;
import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.model.impl.DefaultUserModel;
import msaadawi.blogApi.domain.user.service.UserPersistenceService;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultDtoToUserConverter implements DtoToUserConverter {

    private final UserPersistenceService userPersistenceService;

    @Override
    public UserModel toUser(RequestUserDto source) {
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

    @Override
    public UserModel toUpdatableUser(RequestUserDto source) {
        if (source == null) return null;
        if (source.getId() == null)
            throw new IllegalArgumentException("Bad source. Id is null.");
        UserModel transientUser = new DefaultUserModel();
        UserModel persistedUser = userPersistenceService.getById(source.getId());
        merge(source, transientUser, persistedUser);
        return transientUser;
    }

    private void merge(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        handleId(requestUserDto, transientUser, persistedUser);
        handleUsername(requestUserDto, transientUser, persistedUser);
        handleEmail(requestUserDto, transientUser, persistedUser);
        handlePhone(requestUserDto, transientUser, persistedUser);
        handleFirstName(requestUserDto, transientUser, persistedUser);
        handleLastName(requestUserDto, transientUser, persistedUser);
        handleBirthdate(requestUserDto, transientUser, persistedUser);
        handleProfession(requestUserDto, transientUser, persistedUser);
        handleCurrentLocation(requestUserDto, transientUser, persistedUser);
    }

    private void handleId(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        transientUser.setId(persistedUser.getId());
    }

    private void handleUsername(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        transientUser.setUsername(persistedUser.getUsername());
    }

    private void handleEmail(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        transientUser.setEmail(persistedUser.getEmail());
    }

    private void handlePhone(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        if (requestUserDto.containsPhone())
            transientUser.setPhone(requestUserDto.getPhone());
        else transientUser.setPhone(persistedUser.getPhone());
    }

    private void handleFirstName(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        if (requestUserDto.containsFirstName())
            transientUser.setFirstName(requestUserDto.getFirstName());
        else transientUser.setFirstName(persistedUser.getFirstName());
    }

    private void handleLastName(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        if (requestUserDto.containsLastName())
            transientUser.setLastName(requestUserDto.getLastName());
        else transientUser.setLastName(persistedUser.getLastName());
    }

    private void handleBirthdate(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        if (requestUserDto.containsBirthDate())
            transientUser.setBirthDate(requestUserDto.getBirthDate());
        else transientUser.setBirthDate(persistedUser.getBirthDate());
    }

    private void handleProfession(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        if (requestUserDto.containsProfession())
            transientUser.setProfession(requestUserDto.getProfession());
        else transientUser.setProfession(persistedUser.getProfession());
    }

    private void handleCurrentLocation(RequestUserDto requestUserDto, UserModel transientUser, UserModel persistedUser) {
        if (requestUserDto.containsCurrentLocation())
            transientUser.setCurrentLocation(requestUserDto.getCurrentLocation());
        else transientUser.setCurrentLocation(persistedUser.getCurrentLocation());
    }
}
