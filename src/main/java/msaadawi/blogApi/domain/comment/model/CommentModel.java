package msaadawi.blogApi.domain.comment.model;

import msaadawi.blogApi.commons.model.BaseModel;
import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.post.model.PostModel;

import java.util.Date;

public interface CommentModel extends BaseModel {

    Long getId();

    void setId(Long id);

    String getContent();

    void setContent(String content);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getLastUpdatedAt();

    void setLastUpdatedAt(Date lastUpdatedAt);

    PostModel getPostAddedTo();

    void setPostAddedTo(PostModel post);

    UserModel getOwner();

    void setOwner(UserModel owner);
}
