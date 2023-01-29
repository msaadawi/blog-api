package msaadawi.blogApi.common.web.sorting.impl;

import msaadawi.blogApi.common.web.sorting.SortingConfigurer;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultSortingConfigurer implements SortingConfigurer {

    private static final String ASC = "asc";
    private static final String ASCENDING = "ascending";

    @Override
    public Sort doConfigure(List<? extends SortingSettings> sortingSettingsList) {
        if (sortingSettingsList.isEmpty()) return Sort.unsorted();
        List<Order> orders = new ArrayList<>();
        for (SortingSettings sortingSettings : sortingSettingsList) {
            String sortBy = sortingSettings.getSortBy().trim();
            String sortOrder = sortingSettings.getSortOrder();
            if (sortOrder == null || (sortOrder = sortOrder.trim()).isEmpty())
                orders.add(Order.asc(sortBy)); // default order is asc.
            else {
                if (sortOrder.equalsIgnoreCase(ASC) || sortOrder.equalsIgnoreCase(ASCENDING)) {
                    orders.add(Order.asc(sortBy));
                } else orders.add(Order.desc(sortBy));
            }
        }
        return Sort.by(orders);
    }
}
