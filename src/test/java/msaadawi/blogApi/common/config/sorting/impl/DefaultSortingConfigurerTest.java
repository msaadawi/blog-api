package msaadawi.blogApi.common.config.sorting.impl;

import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.web.sorting.impl.DefaultSortingConfigurer;
import msaadawi.blogApi.common.web.sorting.impl.DefaultSortingSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultSortingConfigurerTest {

    private DefaultSortingConfigurer underTest;

    @BeforeEach
    void setUp() {
        underTest = new DefaultSortingConfigurer();
    }

    @Test
    void should_ReturnUnsorted_When_InputSortingConfigListIsEmpty() {
        // given
        List<SortingSettings> emptySortingConfigList = Collections.emptyList();
        Sort expected = Sort.unsorted();
        // when
        Sort actual = underTest.doConfigure(emptySortingConfigList);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnExpectedSort() {
        // given
        List<SortingSettings> inputSortingConfigList = Arrays.asList(
                new DefaultSortingSettings("id", null),
                new DefaultSortingSettings("id", ""),
                new DefaultSortingSettings("id", " "),
                new DefaultSortingSettings("id", "asc"),
                new DefaultSortingSettings("id", "ascending"),
                new DefaultSortingSettings("id", "desc"),
                new DefaultSortingSettings("id", "descending")
        );
        Sort expected = Sort.by(
                Order.asc("id"),
                Order.asc("id"),
                Order.asc("id"),
                Order.asc("id"),
                Order.asc("id"),
                Order.desc("id"),
                Order.desc("id")
        );
        // when
        Sort actual = underTest.doConfigure(inputSortingConfigList);
        // then
        assertEquals(expected, actual);
    }
}