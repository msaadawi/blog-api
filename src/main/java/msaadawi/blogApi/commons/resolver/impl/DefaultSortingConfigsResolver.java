package msaadawi.blogApi.commons.resolver.impl;

import msaadawi.blogApi.commons.resolver.SortingConfigsResolver;
import msaadawi.blogApi.commons.config.sorting.impl.DefaultSortingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.util.SortingConstants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DefaultSortingConfigsResolver implements SortingConfigsResolver {

    @Override
    public List<SortingConfiguration> doResolve(String[] sort) {
        if (sort == null || sort.length == 0) return Collections.emptyList();
        List<SortingConfiguration> sortingConfigs = new ArrayList<>();
        for (String configPair : sort) {
            if (!configPair.isEmpty()) {
                String[] configPairParts = configPair.split(SortingConstants.PAIR_SEPARATOR, -1);
                String sortBy = configPairParts[0];
                String sortOrder = null;
                if (configPairParts.length > 1)
                    sortOrder = configPairParts[1];
                sortingConfigs.add(new DefaultSortingConfiguration(sortBy, sortOrder));
            }
        }
        return sortingConfigs;
    }
}
