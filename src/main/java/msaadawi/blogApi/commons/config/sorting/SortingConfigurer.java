package msaadawi.blogApi.commons.config.sorting;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface SortingConfigurer {

    Sort doConfigure(List<? extends SortingConfiguration> sortingConfigs);
}
