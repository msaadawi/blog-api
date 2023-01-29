package msaadawi.blogApi.common.web.paging;

public interface PagingSettingsResolver {

    /**
     * Should never return null.
     */
    PagingSettings doResolve(Integer pageNumber, Integer pageSize);
}
