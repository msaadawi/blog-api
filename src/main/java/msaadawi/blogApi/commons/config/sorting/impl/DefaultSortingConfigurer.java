package msaadawi.blogApi.commons.config.sorting.impl;

import msaadawi.blogApi.commons.config.sorting.SortingConfigurer;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.util.SortingConstants;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultSortingConfigurer implements SortingConfigurer {

    @Override
    public Sort doConfigure(List<? extends SortingConfiguration> sortingConfigs) {
        if (sortingConfigs.isEmpty()) return Sort.unsorted();
        List<Order> orders = new ArrayList<>();
        for (SortingConfiguration sortConfig : sortingConfigs) {
            String sortBy = sortConfig.getSortBy().trim();
            String configuredSortOrder = sortConfig.getSortOrder();
            if (configuredSortOrder == null || configuredSortOrder.trim().isEmpty())
                orders.add(Order.asc(sortBy)); // default order is asc.
            else {
                String sortOrder = configuredSortOrder.trim();
                if (sortOrder.equalsIgnoreCase(SortingConstants.ASC_ORDER)
                        || sortOrder.equalsIgnoreCase(SortingConstants.ASCENDING_ORDER)) {
                    orders.add(Order.asc(sortBy));
                } else orders.add(Order.desc(sortBy));
            }
        }
        return Sort.by(orders);
    }
}
