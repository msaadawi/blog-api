package msaadawi.blogApi.domain.post.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.post.converter.PostToEntityConverter;
import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.user.converter.UserToEntityConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultPostToEntityConverter implements PostToEntityConverter {

    private final UserToEntityConverter userToEntityConverter;

    @Override
    public PostEntity toPostEntity(PostModel source) {
        if (source == null) return null;
        return PostEntity.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .owner(userToEntityConverter.toUserEntity(source.getOwner()))
                .build();
    }
}
