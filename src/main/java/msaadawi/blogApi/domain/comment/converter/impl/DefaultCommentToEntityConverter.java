package msaadawi.blogApi.domain.comment.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.comment.converter.CommentToEntityConverter;
import msaadawi.blogApi.domain.comment.data.entity.CommentEntity;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.post.converter.PostToEntityConverter;
import msaadawi.blogApi.domain.user.converter.UserToEntityConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultCommentToEntityConverter implements CommentToEntityConverter {

    private final PostToEntityConverter postToEntityConverter;

    private final UserToEntityConverter userToEntityConverter;

    @Override
    public CommentEntity toCommentEntity(CommentModel source) {
        return CommentEntity.builder()
                .id(source.getId())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .postAddedTo(postToEntityConverter.toPostEntity(source.getPostAddedTo()))
                .owner(userToEntityConverter.toUserEntity(source.getOwner()))
                .build();
    }
}
