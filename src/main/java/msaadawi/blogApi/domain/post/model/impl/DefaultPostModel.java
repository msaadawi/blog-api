package msaadawi.blogApi.domain.post.model.impl;

import lombok.*;
import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.user.model.UserModel;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultPostModel implements PostModel {

    private Long id;

    private String title;

    private String content;

    private Date createdAt;

    private Date lastUpdatedAt;

    private UserModel owner;
}
