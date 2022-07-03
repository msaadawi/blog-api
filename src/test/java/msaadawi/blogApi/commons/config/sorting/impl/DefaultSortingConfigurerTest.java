package msaadawi.blogApi.commons.config.sorting.impl;

import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
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
        List<SortingConfiguration> emptySortingConfigList = Collections.emptyList();
        Sort expected = Sort.unsorted();
        // when
        Sort actual = underTest.doConfigure(emptySortingConfigList);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnExpectedSort() {
        // given
        List<SortingConfiguration> inputSortingConfigList = Arrays.asList(
                new DefaultSortingConfiguration("id", null),
                new DefaultSortingConfiguration("id", ""),
                new DefaultSortingConfiguration("id", " "),
                new DefaultSortingConfiguration("id", "asc"),
                new DefaultSortingConfiguration("id", "ascending"),
                new DefaultSortingConfiguration("id", "desc"),
                new DefaultSortingConfiguration("id", "descending")
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