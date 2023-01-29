package msaadawi.blogApi.domain.post.web.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.domain.post.web.payload.impl.DefaultRequestPostDto;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

import java.util.Date;

@JsonDeserialize(as = DefaultRequestPostDto.class)
public interface RequestPostDto extends PostDto {

    void setId(Long id) throws NoSuchPropertyException;

    boolean containsId() throws NoSuchPropertyException;

    void setTitle(String title) throws NoSuchPropertyException;

    boolean containsTitle() throws NoSuchPropertyException;

    void setContent(String content) throws NoSuchPropertyException;

    boolean containsContent() throws NoSuchPropertyException;

    void setCreatedAt(Date createdAt) throws NoSuchPropertyException;

    boolean containsCreatedAt() throws NoSuchPropertyException;

    void setLastUpdatedAt(Date lastUpdatedAt) throws NoSuchPropertyException;

    boolean containsLastUpdatedAt() throws NoSuchPropertyException;

    RequestUserDto getOwner() throws NoSuchPropertyException;

    void setOwner(RequestUserDto owner) throws NoSuchPropertyException;

    boolean containsOwner() throws NoSuchPropertyException;
}
