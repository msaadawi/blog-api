package msaadawi.blogApi.domain.user.web.payload;

import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.common.payload.BaseDto;

import java.util.Date;

public interface UserDto extends BaseDto {

    Long getId() throws NoSuchPropertyException;

    String getUsername() throws NoSuchPropertyException;

    String getEmail() throws NoSuchPropertyException;

    String getPhone() throws NoSuchPropertyException;

    String getFirstName() throws NoSuchPropertyException;

    String getLastName() throws NoSuchPropertyException;

    Date getBirthDate() throws NoSuchPropertyException;

    String getProfession() throws NoSuchPropertyException;

    String getCurrentLocation() throws NoSuchPropertyException;
}
