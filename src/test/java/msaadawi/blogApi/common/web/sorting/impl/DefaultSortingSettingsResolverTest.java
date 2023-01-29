package msaadawi.blogApi.common.web.sorting.impl;

import msaadawi.blogApi.common.web.sorting.SortingSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSortingSettingsResolverTest {

    private DefaultSortingSettingsResolver underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new DefaultSortingSettingsResolver();
    }

    @Test
    void should_ReturnEmptyList_When_SortInputArrayIsNull() {
        // given
        String[] nullSortArray = null;
        int expected = 0;
        // when
        List<SortingSettings> sortingSettingsList = underTest.doResolve(nullSortArray);
        assertNotNull(sortingSettingsList);
        int actual = sortingSettingsList.size();
        // then
        assertEquals(actual, expected);
    }

    @Test
    void should_ReturnEmptyList_When_SortInputArrayIsEmpty() {
        // given
        String[] emptySortArray = new String[0];
        int expected = 0;
        // when
        List<SortingSettings> sortingSettingsList = underTest.doResolve(emptySortArray);
        assertNotNull(sortingSettingsList);
        int actual = sortingSettingsList.size();
        // then
        assertEquals(actual, expected);
    }

    @Test
    void should_ReturnExpectedSortingConfigList() {
        // given
        String[] sort = new String[]{"", "id", "id:", "id:asc"};
        List<SortingSettings> expected = Arrays.asList(
                new DefaultSortingSettings("id", null),
                new DefaultSortingSettings("id", ""),
                new DefaultSortingSettings("id", "asc"));
        // when
        List<SortingSettings> actual = underTest.doResolve(sort);
        // then
        assertIterableEquals(expected, actual);
    }
}