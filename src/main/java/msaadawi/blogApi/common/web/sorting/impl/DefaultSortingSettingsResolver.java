package msaadawi.blogApi.common.web.sorting.impl;

import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.web.sorting.SortingSettingsResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DefaultSortingSettingsResolver implements SortingSettingsResolver {

    private static final String SORTING_SETTINGS_SEPARATOR = ":";

    @Override
    public List<SortingSettings> doResolve(String[] sort) {
        if (sort == null || sort.length == 0) return Collections.emptyList();
        List<SortingSettings> sortingSettingsList = new ArrayList<>();
        for (String rawSettings : sort) {
            if (!rawSettings.isEmpty()) {
                String[] rawSettingsParts = rawSettings.split(SORTING_SETTINGS_SEPARATOR, -1);
                String sortBy = rawSettingsParts[0];
                String sortOrder = null;
                if (rawSettingsParts.length > 1) sortOrder = rawSettingsParts[1];
                sortingSettingsList.add(new DefaultSortingSettings(sortBy, sortOrder));
            }
        }
        return sortingSettingsList;
    }
}
