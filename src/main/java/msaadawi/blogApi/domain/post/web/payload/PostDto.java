package msaadawi.blogApi.domain.post.web.payload;

import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.common.payload.BaseDto;
import msaadawi.blogApi.domain.user.web.payload.UserDto;

import java.util.Date;

public interface PostDto extends BaseDto {

    Long getId() throws NoSuchPropertyException;

    String getTitle() throws NoSuchPropertyException;

    String getContent() throws NoSuchPropertyException;

    Date getCreatedAt() throws NoSuchPropertyException;

    Date getLastUpdatedAt() throws NoSuchPropertyException;

    UserDto getOwner() throws NoSuchPropertyException;
}
