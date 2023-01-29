package msaadawi.blogApi.domain.comment.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.comment.converter.EntityToCommentConverter;
import msaadawi.blogApi.domain.comment.data.entity.CommentEntity;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.model.impl.DefaultCommentModel;
import msaadawi.blogApi.domain.post.converter.EntityToPostConverter;
import msaadawi.blogApi.domain.user.converter.EntityToUserConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultEntityToCommentConverter implements EntityToCommentConverter {

    private final EntityToPostConverter entityToPostConverter;

    private final EntityToUserConverter entityToUserConverter;

    @Override
    public CommentModel toComment(CommentEntity source) {
        return DefaultCommentModel.builder()
                .id(source.getId())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .post(entityToPostConverter.toPost(source.getPost()))
                .owner(entityToUserConverter.toUser(source.getOwner()))
                .build();
    }
}
