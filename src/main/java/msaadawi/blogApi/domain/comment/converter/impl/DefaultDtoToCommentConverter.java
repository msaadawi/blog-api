package msaadawi.blogApi.domain.comment.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.comment.converter.DtoToCommentConverter;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.model.impl.DefaultCommentModel;
import msaadawi.blogApi.domain.comment.service.CommentPersistenceService;
import msaadawi.blogApi.domain.comment.web.payload.RequestCommentDto;
import msaadawi.blogApi.domain.post.converter.DtoToPostConverter;
import msaadawi.blogApi.domain.user.converter.DtoToUserConverter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class DefaultDtoToCommentConverter implements DtoToCommentConverter {

    private final DtoToPostConverter dtoToPostConverter;

    private final DtoToUserConverter dtoToUserConverter;

    private final CommentPersistenceService commentPersistenceService;

    @Override
    public CommentModel toComment(RequestCommentDto source) {
        return DefaultCommentModel.builder()
                .id(source.getId())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .post(dtoToPostConverter.toPost(source.getPost()))
                .owner(dtoToUserConverter.toUser(source.getOwner()))
                .build();
    }

    @Override
    public CommentModel toUpdatableComment(RequestCommentDto source) {
        if (source == null) return null;
        if (source.getId() == null)
            throw new IllegalArgumentException("Bad source, id is null.");
        CommentModel transientComment = new DefaultCommentModel();
        CommentModel persistedComment = commentPersistenceService.getById(source.getId());
        merge(source, transientComment, persistedComment);
        return transientComment;
    }

    private void merge(RequestCommentDto requestCommentDto, CommentModel transientComment, CommentModel persistedComment) {
        handleId(requestCommentDto, transientComment, persistedComment);
        boolean contentHasChanged = handleContent(requestCommentDto, transientComment, persistedComment);
        handleCreatedAt(requestCommentDto, transientComment, persistedComment);
        handlePost(requestCommentDto, transientComment, persistedComment);
        handleOwner(requestCommentDto, transientComment, persistedComment);
        if (contentHasChanged)
            transientComment.setLastUpdatedAt(Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS)));
        else transientComment.setLastUpdatedAt(persistedComment.getLastUpdatedAt());
    }

    private void handleId(RequestCommentDto reqCommentDto, CommentModel transientComment, CommentModel persistedComment) {
        transientComment.setId(persistedComment.getId());
    }

    private boolean handleContent(RequestCommentDto reqCommentDto, CommentModel transientComment, CommentModel persistedComment) {
        boolean contentHasChanged = false;
        if (reqCommentDto.containsContent()) {
            transientComment.setContent(reqCommentDto.getContent());
            if (!reqCommentDto.getContent().equals(persistedComment.getContent()))
                contentHasChanged = true;
        } else transientComment.setContent(persistedComment.getContent());
        return contentHasChanged;
    }

    private void handleCreatedAt(RequestCommentDto reqCommentDto, CommentModel transientComment, CommentModel persistedComment) {
        transientComment.setCreatedAt(persistedComment.getCreatedAt());
    }

    private void handlePost(RequestCommentDto reqCommentDto, CommentModel transientComment, CommentModel persistedComment) {
        transientComment.setPost(persistedComment.getPost());
    }

    private void handleOwner(RequestCommentDto reqCommentDto, CommentModel transientComment, CommentModel persistedComment) {
        transientComment.setOwner(persistedComment.getOwner());
    }
}
