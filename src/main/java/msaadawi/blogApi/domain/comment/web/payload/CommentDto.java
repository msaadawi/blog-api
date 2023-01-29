package msaadawi.blogApi.domain.comment.web.payload;

import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.common.payload.BaseDto;
import msaadawi.blogApi.domain.post.web.payload.PostDto;
import msaadawi.blogApi.domain.user.web.payload.UserDto;

import java.util.Date;

public interface CommentDto extends BaseDto {

    Long getId() throws NoSuchPropertyException;

    String getContent() throws NoSuchPropertyException;

    Date getCreatedAt() throws NoSuchPropertyException;

    Date getLastUpdatedAt() throws NoSuchPropertyException;

    PostDto getPost() throws NoSuchPropertyException;

    UserDto getOwner() throws NoSuchPropertyException;
}
