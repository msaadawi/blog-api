package msaadawi.blogApi.commons.resolver.impl;

import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.paging.impl.DefaultPagingConfiguration;
import msaadawi.blogApi.commons.resolver.PagingConfigResolver;
import org.springframework.stereotype.Component;

@Component
public class DefaultPagingConfigResolver implements PagingConfigResolver {

    @Override
    public PagingConfiguration doResolve(Integer pageNumber, Integer pageSize) {
        return new DefaultPagingConfiguration(pageNumber, pageSize);
    }
}
