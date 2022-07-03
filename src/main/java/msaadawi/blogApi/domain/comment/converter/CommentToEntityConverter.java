package msaadawi.blogApi.domain.comment.converter;

import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.data.entity.CommentEntity;

import java.util.List;

public interface CommentToEntityConverter {

    CommentEntity toCommentEntity(CommentModel source);

    default List<CommentEntity> toCommentEntityList(List<? extends CommentModel> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toCommentEntity).toList();
    }
}
