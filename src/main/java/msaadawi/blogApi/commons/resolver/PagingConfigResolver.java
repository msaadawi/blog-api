package msaadawi.blogApi.commons.resolver;

import msaadawi.blogApi.commons.config.paging.PagingConfiguration;

public interface PagingConfigResolver {

    /**
     * Should never return null.
     */
    PagingConfiguration doResolve(Integer pageNumber, Integer pageSize);
}
