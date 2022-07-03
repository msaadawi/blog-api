package msaadawi.blogApi.domain.user.web.payload;

import msaadawi.blogApi.commons.exception.NoSuchPropertyException;

import java.util.Date;

public interface ResponseUserDto extends UserDto {

    void setId(Long id) throws NoSuchPropertyException;

    void setUsername(String username) throws NoSuchPropertyException;

    void setEmail(String email) throws NoSuchPropertyException;

    void setPhone(String phone) throws NoSuchPropertyException;

    void setFirstName(String firstName) throws NoSuchPropertyException;

    void setLastName(String lastName) throws NoSuchPropertyException;

    void setBirthDate(Date birthDate) throws NoSuchPropertyException;

    void setProfession(String profession) throws NoSuchPropertyException;

    void setCurrentLocation(String currentLocation) throws NoSuchPropertyException;
}
