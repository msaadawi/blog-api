package msaadawi.blogApi.domain.comment.web.payload;

import msaadawi.blogApi.commons.exception.NoSuchPropertyException;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;

public interface RequestCommentDto extends CommentDto {

    boolean containsId();

    boolean containsContent();

    boolean containsCreatedAt();

    boolean containsLastUpdatedAt();

    RequestPostDto getPostAddedTo() throws NoSuchPropertyException;

    boolean containsPostAddedTo();

    RequestUserDto getOwner() throws NoSuchPropertyException;

    boolean containsOwner();
}
