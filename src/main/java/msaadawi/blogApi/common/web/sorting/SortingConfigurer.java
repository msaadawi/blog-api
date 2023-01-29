package msaadawi.blogApi.common.web.sorting;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface SortingConfigurer {

    Sort doConfigure(List<? extends SortingSettings> sortingSettingsList);
}
