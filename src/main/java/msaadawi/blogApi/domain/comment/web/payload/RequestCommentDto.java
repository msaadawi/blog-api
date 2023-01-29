package msaadawi.blogApi.domain.comment.web.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.domain.comment.web.payload.impl.DefaultRequestCommentDto;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import java.util.Date;

@JsonDeserialize(as = DefaultRequestCommentDto.class)
public interface RequestCommentDto extends CommentDto {

    void setId(Long id) throws NoSuchPropertyException;

    boolean containsId() throws NoSuchPropertyException;

    void setContent(String content) throws NoSuchPropertyException;

    boolean containsContent() throws NoSuchPropertyException;

    void setCreatedAt(Date createdAt) throws NoSuchPropertyException;

    boolean containsCreatedAt() throws NoSuchPropertyException;

    void setLastUpdatedAt(Date lastUpdatedAt) throws NoSuchPropertyException;

    boolean containsLastUpdatedAt() throws NoSuchPropertyException;

    RequestPostDto getPost() throws NoSuchPropertyException;

    void setPost(RequestPostDto post) throws NoSuchPropertyException;

    boolean containsPost() throws NoSuchPropertyException;

    RequestUserDto getOwner() throws NoSuchPropertyException;

    void setOwner(RequestUserDto owner) throws NoSuchPropertyException;

    boolean containsOwner() throws NoSuchPropertyException;
}
