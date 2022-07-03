package msaadawi.blogApi.commons.config.paging.impl;

import msaadawi.blogApi.commons.config.paging.PagingConfigurer;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.util.PagingConstants;
import org.springframework.stereotype.Component;

@Component
public class DefaultPagingConfigurer implements PagingConfigurer {

    @Override
    public int configurePageNumber(PagingConfiguration pagingConfig) {
        if (pagingConfig.getPageNumber() == null)
            return PagingConstants.DEFAULT_PAGE_NUMBER - 1;
        return pagingConfig.getPageNumber() - 1;
    }

    @Override
    public int configurePageSize(PagingConfiguration pagingConfig) {
        if (pagingConfig.getPageSize() == null)
            return PagingConstants.DEFAULT_PAGE_SIZE;
        return pagingConfig.getPageSize();
    }
}
