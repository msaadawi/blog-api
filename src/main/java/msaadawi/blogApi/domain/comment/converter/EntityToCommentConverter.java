package msaadawi.blogApi.domain.comment.converter;

import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.data.entity.CommentEntity;

import java.util.List;

public interface EntityToCommentConverter {

    CommentModel toComment(CommentEntity source);

    default List<CommentModel> toCommentList(List<CommentEntity> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toComment).toList();
    }
}
