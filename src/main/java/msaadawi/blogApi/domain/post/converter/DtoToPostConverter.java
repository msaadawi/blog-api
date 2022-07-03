package msaadawi.blogApi.domain.post.converter;

import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;

import java.util.List;

public interface DtoToPostConverter {

    PostModel toPost(RequestPostDto source);

    default List<PostModel> toPostList(List<? extends RequestPostDto> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toPost).toList();
    }

    /**
     * converts a dto that is intended for update operation to a model object.
     *
     * @param source the dto carrying the update data.
     * @return a changed model to be synced with the data store.
     */
    PostModel toUpdatablePost(RequestPostDto source);

    default List<PostModel> toUpdatablePostList(List<? extends RequestPostDto> sourceList) {
        return sourceList.stream()
                .map(this::toUpdatablePost)
                .toList();
    }
}
