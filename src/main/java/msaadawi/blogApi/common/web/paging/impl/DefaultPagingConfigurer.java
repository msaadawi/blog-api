package msaadawi.blogApi.common.web.paging.impl;

import msaadawi.blogApi.common.web.paging.PagingConfigurer;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import org.springframework.stereotype.Component;

@Component
public class DefaultPagingConfigurer implements PagingConfigurer {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Override
    public int configurePageNumber(PagingSettings pagingSettings) {
        if (pagingSettings.getPageNumber() == null)
            return DEFAULT_PAGE_NUMBER - 1;
        return pagingSettings.getPageNumber() - 1;
    }

    @Override
    public int configurePageSize(PagingSettings pagingSettings) {
        if (pagingSettings.getPageSize() == null)
            return DEFAULT_PAGE_SIZE;
        return pagingSettings.getPageSize();
    }
}
