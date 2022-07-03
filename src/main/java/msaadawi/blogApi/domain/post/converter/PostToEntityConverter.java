package msaadawi.blogApi.domain.post.converter;

import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import msaadawi.blogApi.domain.post.model.PostModel;

import java.util.List;

public interface PostToEntityConverter {

    PostEntity toPostEntity(PostModel source);

    default List<PostEntity> toPostEntityList(List<? extends PostModel> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toPostEntity).toList();
    }
}
