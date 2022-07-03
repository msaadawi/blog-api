package msaadawi.blogApi.domain.post.converter.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.post.converter.DtoToPostConverter;
import msaadawi.blogApi.domain.post.model.impl.DefaultPostModel;
import msaadawi.blogApi.domain.user.converter.DtoToUserConverter;
import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.post.service.PostPersistenceService;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class DefaultDtoToPostConverter implements DtoToPostConverter {

    private final DtoToUserConverter dtoToUserConverter;

    private final PostPersistenceService postPersistenceService;

    @Override
    public PostModel toPost(RequestPostDto source) {
        if (source == null) return null;
        return DefaultPostModel.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .lastUpdatedAt(source.getLastUpdatedAt())
                .owner(dtoToUserConverter.toUser(source.getOwner()))
                .build();
    }

    @Override
    public PostModel toUpdatablePost(RequestPostDto source) {
        if (source == null) return null;
        if (source.getId() == null)
            throw new IllegalArgumentException("Bad source, id is null.");
        PostModel transientPost = new DefaultPostModel();
        PostModel persistedPost = postPersistenceService.getById(source.getId());
        merge(source, transientPost, persistedPost);
        return transientPost;
    }

    private void merge(RequestPostDto requestPostDto, PostModel transientPost, PostModel persistedPost) {
        handleId(requestPostDto, transientPost, persistedPost);
        boolean titleHasChanged = handleTitle(requestPostDto, transientPost, persistedPost);
        boolean contentHasChanged = handleContent(requestPostDto, transientPost, persistedPost);
        handleCreatedAt(requestPostDto, transientPost, persistedPost);
        handleOwner(requestPostDto, transientPost, persistedPost);
        if (titleHasChanged || contentHasChanged)
            transientPost.setLastUpdatedAt(Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS)));
        else transientPost.setLastUpdatedAt(persistedPost.getLastUpdatedAt());
    }

    private void handleId(RequestPostDto requestPostDto, PostModel transientPost, PostModel persistedPost) {
        transientPost.setId(persistedPost.getId());
    }

    /**
     * merges the post's title and return true if it has changed, false otherwise.
     *
     * @param requestPostDto the post data transfer object.
     * @param transientPost  the transient post object.
     * @param persistedPost  the post object stored in the data store.
     * @return true if title has changed, false otherwise.
     */
    private boolean handleTitle(RequestPostDto requestPostDto, PostModel transientPost, PostModel persistedPost) {
        boolean titleHasChanged = false;
        if (requestPostDto.containsTitle()) {
            transientPost.setTitle(requestPostDto.getTitle());
            if (!requestPostDto.getTitle().equals(persistedPost.getTitle()))
                titleHasChanged = true;
        } else transientPost.setTitle(persistedPost.getTitle());
        return titleHasChanged;
    }

    /**
     * merges the post's content and return true if it has changed, false otherwise.
     *
     * @param requestPostDto the post data transfer object.
     * @param transientPost  the transient post object.
     * @param persistedPost  the post object stored in the data store.
     * @return true if content has changed, false otherwise.
     */
    private boolean handleContent(RequestPostDto requestPostDto, PostModel transientPost, PostModel persistedPost) {
        boolean contentHasChanged = false;
        if (requestPostDto.containsContent()) {
            transientPost.setContent(requestPostDto.getContent());
            if (!requestPostDto.getContent().equals(persistedPost.getContent()))
                contentHasChanged = true;
        } else transientPost.setContent(persistedPost.getContent());
        return contentHasChanged;
    }

    private void handleCreatedAt(RequestPostDto requestPostDto, PostModel transientPost, PostModel persistedPost) {
        transientPost.setCreatedAt(persistedPost.getCreatedAt());
    }

    private void handleOwner(RequestPostDto requestPostDto, PostModel transientPost, PostModel persistedPost) {
        transientPost.setOwner(persistedPost.getOwner());
    }
}
