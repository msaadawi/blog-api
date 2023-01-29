package msaadawi.blogApi.common.web.paging.impl;

import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.web.paging.PagingSettingsResolver;
import org.springframework.stereotype.Component;

@Component
public class DefaultPagingSettingsResolver implements PagingSettingsResolver {

    @Override
    public PagingSettings doResolve(Integer pageNumber, Integer pageSize) {
        return new DefaultPagingSettings(pageNumber, pageSize);
    }
}
