package msaadawi.blogApi.domain.post.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.post.converter.PostToDtoConverter;
import msaadawi.blogApi.domain.post.web.payload.ResponsePostDto;
import msaadawi.blogApi.domain.user.converter.UserToDtoConverter;
import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.post.web.payload.impl.DefaultResponsePostDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultPostToDtoConverter implements PostToDtoConverter {

    private final UserToDtoConverter userToDtoConverter;

    @Override
    public ResponsePostDto toPostDto(PostModel source) {
        if (source == null) return null;
        return DefaultResponsePostDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .owner(userToDtoConverter.toUserDto(source.getOwner()))
                .build();
    }
}
