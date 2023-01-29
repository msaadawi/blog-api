package msaadawi.blogApi.domain.comment.web.payload;

import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.domain.post.web.payload.ResponsePostDto;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;

import java.util.Date;

public interface ResponseCommentDto extends CommentDto {

    void setId(Long id) throws NoSuchPropertyException;

    void setContent(String content) throws NoSuchPropertyException;

    void setCreatedAt(Date createdAt) throws NoSuchPropertyException;

    void setLastUpdatedAt(Date lastUpdatedAt) throws NoSuchPropertyException;

    ResponsePostDto getPost() throws NoSuchPropertyException;

    void setPost(ResponsePostDto post) throws NoSuchPropertyException;

    ResponseUserDto getOwner() throws NoSuchPropertyException;

    void setOwner(ResponseUserDto owner) throws NoSuchPropertyException;
}
