package msaadawi.blogApi.domain.user.web.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.domain.user.web.payload.impl.DefaultRequestUserDto;

import java.util.Date;

@JsonDeserialize(as = DefaultRequestUserDto.class)
public interface RequestUserDto extends UserDto {

    void setId(Long id) throws NoSuchPropertyException;

    boolean containsId() throws NoSuchPropertyException;

    void setUsername(String username) throws NoSuchPropertyException;

    boolean containsUsername() throws NoSuchPropertyException;

    void setEmail(String email) throws NoSuchPropertyException;

    boolean containsEmail() throws NoSuchPropertyException;

    void setPhone(String phone) throws NoSuchPropertyException;

    boolean containsPhone() throws NoSuchPropertyException;

    void setFirstName(String firstName) throws NoSuchPropertyException;

    boolean containsFirstName() throws NoSuchPropertyException;

    void setLastName(String lastName) throws NoSuchPropertyException;

    boolean containsLastName() throws NoSuchPropertyException;

    void setBirthDate(Date birthDate) throws NoSuchPropertyException;

    boolean containsBirthDate() throws NoSuchPropertyException;

    void setProfession(String profession) throws NoSuchPropertyException;

    boolean containsProfession() throws NoSuchPropertyException;

    void setCurrentLocation(String currentLocation) throws NoSuchPropertyException;

    boolean containsCurrentLocation() throws NoSuchPropertyException;
}
