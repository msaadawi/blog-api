package msaadawi.blogApi.domain.post.model;

import msaadawi.blogApi.commons.model.BaseModel;
import msaadawi.blogApi.domain.user.model.UserModel;

import java.util.Date;

public interface PostModel extends BaseModel {

    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getContent();

    void setContent(String content);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getLastUpdatedAt();

    void setLastUpdatedAt(Date lastUpdatedAt);

    UserModel getOwner();

    void setOwner(UserModel owner);
}
