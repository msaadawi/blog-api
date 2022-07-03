package msaadawi.blogApi.domain.post.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.post.converter.EntityToPostConverter;
import msaadawi.blogApi.domain.post.model.impl.DefaultPostModel;
import msaadawi.blogApi.domain.user.converter.EntityToUserConverter;
import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import msaadawi.blogApi.domain.post.model.PostModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultEntityToPostConverter implements EntityToPostConverter {

    private final EntityToUserConverter entityToUserConverter;

    @Override
    public PostModel toPost(PostEntity source) {
        if (source == null) return null;
        return DefaultPostModel.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .owner(entityToUserConverter.toUser(source.getOwner()))
                .build();
    }
}
