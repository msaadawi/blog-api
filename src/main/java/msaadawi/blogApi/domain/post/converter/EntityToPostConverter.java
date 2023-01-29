package msaadawi.blogApi.domain.post.converter;

import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import msaadawi.blogApi.domain.post.model.PostModel;

import java.util.List;

public interface EntityToPostConverter {

    PostModel toPost(PostEntity source);

    default List<PostModel> toPostList(List<PostEntity> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toPost).toList();
    }
}
