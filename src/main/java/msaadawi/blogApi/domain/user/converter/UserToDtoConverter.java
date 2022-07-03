package msaadawi.blogApi.domain.user.converter;

import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;

import java.util.List;

public interface UserToDtoConverter {

    ResponseUserDto toUserDto(UserModel source);

    default List<ResponseUserDto> toUserDtoList(List<? extends UserModel> sourceList) {
        if (sourceList == null) return null;
        return sourceList.stream().map(this::toUserDto).toList();
    }

    default <T extends UserModel> PageResult<ResponseUserDto> toUserDtoPageResult(PageResult<T> sourcePageResult) {
        if (sourcePageResult == null) return null;
        PageResult<ResponseUserDto> ret = new PageResult<>();
        List<T> sourceElements = sourcePageResult.getElements();
        if (sourceElements != null)
            for (T source : sourceElements)
                ret.getElements().add(this.toUserDto(source));
        ret.setPageSize(sourcePageResult.getPageSize());
        ret.setTotalSize(sourcePageResult.getTotalSize());
        ret.setLastPage(sourcePageResult.isLastPage());
        return ret;
    }
}
