package msaadawi.blogApi.commons.resolver.impl;

import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.config.sorting.impl.DefaultSortingConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSortingConfigsResolverTest {

    private DefaultSortingConfigsResolver underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new DefaultSortingConfigsResolver();
    }

    @Test
    void should_ReturnEmptyList_When_SortInputArrayIsNull() {
        // given
        String[] nullSortArray = null;
        int expected = 0;
        // when
        List<SortingConfiguration> sortingConfigurationList = underTest.doResolve(nullSortArray);
        assertNotNull(sortingConfigurationList);
        int actual = sortingConfigurationList.size();
        // then
        assertEquals(actual, expected);
    }

    @Test
    void should_ReturnEmptyList_When_SortInputArrayIsEmpty() {
        // given
        String[] emptySortArray = new String[0];
        int expected = 0;
        // when
        List<SortingConfiguration> sortingConfigurationList = underTest.doResolve(emptySortArray);
        assertNotNull(sortingConfigurationList);
        int actual = sortingConfigurationList.size();
        // then
        assertEquals(actual, expected);
    }

    @Test
    void should_ReturnExpectedSortingConfigList() {
        // given
        String[] sort = new String[]{"", "id", "id:", "id:asc"};
        List<SortingConfiguration> expected = Arrays.asList(
                new DefaultSortingConfiguration("id", null),
                new DefaultSortingConfiguration("id", ""),
                new DefaultSortingConfiguration("id", "asc"));
        // when
        List<SortingConfiguration> actual = underTest.doResolve(sort);
        // then
        assertIterableEquals(expected, actual);
    }
}