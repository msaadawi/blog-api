package msaadawi.blogApi.domain.comment.converter;

import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.web.payload.ResponseCommentDto;
import msaadawi.blogApi.commons.util.PageResult;

import java.util.List;

public interface CommentToDtoConverter {

    ResponseCommentDto toCommentDto(CommentModel source);

    default List<ResponseCommentDto> toCommentDtoList(List<? extends CommentModel> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().sorted().map(this::toCommentDto).toList();
    }

    default <T extends CommentModel> PageResult<ResponseCommentDto> toCommentDtoPageResult(PageResult<T> sourcePageResult) {
        if (sourcePageResult == null) return null;
        PageResult<ResponseCommentDto> ret = new PageResult<>();
        List<T> sourceElements = sourcePageResult.getElements();
        if (sourceElements != null) {
            for (T source : sourceElements)
                ret.getElements().add(this.toCommentDto(source));
        }
        ret.setPageSize(sourcePageResult.getPageSize());
        ret.setTotalSize(sourcePageResult.getTotalSize());
        ret.setLastPage(sourcePageResult.isLastPage());
        return ret;
    }
}
