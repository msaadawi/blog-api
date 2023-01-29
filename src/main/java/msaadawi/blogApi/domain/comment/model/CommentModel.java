package msaadawi.blogApi.domain.comment.model;

import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.user.model.UserModel;

import java.util.Date;

public interface CommentModel {

    Long getId();

    void setId(Long id);

    String getContent();

    void setContent(String content);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getLastUpdatedAt();

    void setLastUpdatedAt(Date lastUpdatedAt);

    PostModel getPost();

    void setPost(PostModel post);

    UserModel getOwner();

    void setOwner(UserModel owner);
}
