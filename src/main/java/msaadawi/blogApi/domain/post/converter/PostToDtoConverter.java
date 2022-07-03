package msaadawi.blogApi.domain.post.converter;

import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.domain.post.web.payload.ResponsePostDto;
import msaadawi.blogApi.domain.post.model.PostModel;

import java.util.List;

public interface PostToDtoConverter {

    ResponsePostDto toPostDto(PostModel source);

    default List<ResponsePostDto> toPostDtoList(List<? extends PostModel> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toPostDto).toList();
    }

    default <T extends PostModel> PageResult<ResponsePostDto> toPostDtoPageResult(PageResult<T> sourcePageResult) {
        if (sourcePageResult == null) return null;
        PageResult<ResponsePostDto> ret = new PageResult<>();
        List<T> sourceElements = sourcePageResult.getElements();
        if (sourceElements != null)
            for (T source : sourceElements)
                ret.getElements().add(this.toPostDto(source));
        ret.setPageSize(sourcePageResult.getPageSize());
        ret.setTotalSize(sourcePageResult.getTotalSize());
        ret.setLastPage(sourcePageResult.isLastPage());
        return ret;
    }
}
