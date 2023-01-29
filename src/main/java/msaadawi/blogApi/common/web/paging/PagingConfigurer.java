package msaadawi.blogApi.common.web.paging;

public interface PagingConfigurer {

    int configurePageNumber(PagingSettings pagingConfig);

    int configurePageSize(PagingSettings pagingConfig);
}
