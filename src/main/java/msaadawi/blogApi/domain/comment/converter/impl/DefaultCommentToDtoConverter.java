package msaadawi.blogApi.domain.comment.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.comment.converter.CommentToDtoConverter;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.web.payload.ResponseCommentDto;
import msaadawi.blogApi.domain.comment.web.payload.impl.DefaultResponseCommentDto;
import msaadawi.blogApi.domain.post.converter.PostToDtoConverter;
import msaadawi.blogApi.domain.user.converter.UserToDtoConverter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultCommentToDtoConverter implements CommentToDtoConverter {

    private final PostToDtoConverter postToDtoConverter;

    private final UserToDtoConverter userToDtoConverter;

    @Override
    public ResponseCommentDto toCommentDto(CommentModel source) {
        if (source == null) return null;
        return DefaultResponseCommentDto.builder()
                .id(source.getId())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .postAddedTo(postToDtoConverter.toPostDto(source.getPostAddedTo()))
                .owner(userToDtoConverter.toUserDto(source.getOwner()))
                .build();
    }
}
