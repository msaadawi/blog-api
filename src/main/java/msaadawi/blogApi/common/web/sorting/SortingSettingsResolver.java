package msaadawi.blogApi.common.web.sorting;

import java.util.List;

public interface SortingSettingsResolver {

    /**
     * Should never return null.
     */
    List<SortingSettings> doResolve(String[] sort);
}
