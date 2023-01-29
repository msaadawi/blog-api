package msaadawi.blogApi.domain.post.web.payload;

import msaadawi.blogApi.common.exception.NoSuchPropertyException;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;

import java.util.Date;

public interface ResponsePostDto extends PostDto {

    void setId(Long id) throws NoSuchPropertyException;

    void setTitle(String title) throws NoSuchPropertyException;

    void setContent(String content) throws NoSuchPropertyException;

    void setCreatedAt(Date createdAt) throws NoSuchPropertyException;

    void setLastUpdatedAt(Date lastUpdatedAt) throws NoSuchPropertyException;

    ResponseUserDto getOwner() throws NoSuchPropertyException;

    void setOwner(ResponseUserDto owner) throws NoSuchPropertyException;
}
