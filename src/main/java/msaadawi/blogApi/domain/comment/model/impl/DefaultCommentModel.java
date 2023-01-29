package msaadawi.blogApi.domain.comment.model.impl;

import lombok.*;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.user.model.UserModel;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DefaultCommentModel implements CommentModel {

    private Long id;

    private String content;

    private Date createdAt;

    private Date lastUpdatedAt;

    private PostModel post;

    private UserModel owner;
}
