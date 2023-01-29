package msaadawi.blogApi.domain.comment.converter;

import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.web.payload.RequestCommentDto;

import java.util.List;

public interface DtoToCommentConverter {

    CommentModel toComment(RequestCommentDto source);

    default List<CommentModel> toCommentList(List<? extends RequestCommentDto> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toComment).toList();
    }

    /**
     * converts a dto that is intended for update operation to a model object
     * ready to be synced with the datastore.
     *
     * @param source the dto carrying the update data.
     * @return a changed model to be synced with the data store.
     */
    CommentModel toUpdatableComment(RequestCommentDto source);

    default List<CommentModel> toUpdatableCommentList(List<? extends RequestCommentDto> sourceList) {
        return sourceList.stream()
                .map(this::toUpdatableComment)
                .toList();
    }
}
