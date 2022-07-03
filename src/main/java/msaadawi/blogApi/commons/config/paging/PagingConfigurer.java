package msaadawi.blogApi.commons.config.paging;

public interface PagingConfigurer {

    int configurePageNumber(PagingConfiguration pagingConfig);

    int configurePageSize(PagingConfiguration pagingConfig);
}
