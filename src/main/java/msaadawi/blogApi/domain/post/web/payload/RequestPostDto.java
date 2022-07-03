package msaadawi.blogApi.domain.post.web.payload;

import msaadawi.blogApi.commons.exception.NoSuchPropertyException;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

public interface RequestPostDto extends PostDto {

    boolean containsId();

    boolean containsTitle();

    boolean containsContent();

    boolean containsCreatedAt();

    boolean containsLastUpdatedAt();

    RequestUserDto getOwner() throws NoSuchPropertyException;

    boolean containsOwner();
}
