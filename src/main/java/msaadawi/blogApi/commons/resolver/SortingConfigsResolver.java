package msaadawi.blogApi.commons.resolver;

import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;

import java.util.List;

public interface SortingConfigsResolver {

    /**
     * Should never return null.
     */
    List<SortingConfiguration> doResolve(String[] sort);
}
