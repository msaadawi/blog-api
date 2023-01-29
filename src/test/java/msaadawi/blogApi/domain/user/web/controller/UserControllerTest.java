package msaadawi.blogApi.domain.user.web.controller;

import msaadawi.blogApi.common.exception.EntityNotFoundException;
import msaadawi.blogApi.common.web.paging.PagingSettingsResolver;
import msaadawi.blogApi.common.web.sorting.SortingSettingsResolver;
import msaadawi.blogApi.common.validation.validator.PagingSettingsValidator;
import msaadawi.blogApi.common.validation.validator.SortingSettingsValidator;
import msaadawi.blogApi.domain.user.converter.DtoToUserConverter;
import msaadawi.blogApi.domain.user.converter.UserToDtoConverter;
import msaadawi.blogApi.domain.user.service.UserPersistenceService;
import msaadawi.blogApi.domain.user.validator.UserRequestPayloadValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPersistenceService userPersistenceService;

    @MockBean
    private UserToDtoConverter userToDtoConverter;

    @MockBean
    private DtoToUserConverter dtoToUserConverter;

    @MockBean
    private UserRequestPayloadValidator reqPayloadValidator;

    @MockBean
    private PagingSettingsResolver pagingSettingsResolver;

    @MockBean
    private PagingSettingsValidator pagingSettingsValidator;

    @MockBean
    private SortingSettingsResolver sortingSettingsResolver;

    @MockBean
    private SortingSettingsValidator sortingSettingsValidator;

    @Test
    void should_Return404NotFound_For_GetUser_When_UserDoesNotExist() throws Exception {
        // given
        long id = 0L;
        given(userPersistenceService.getById(id)).willThrow(EntityNotFoundException.class);
        int expected = 404;
        // when
        int actual = mockMvc.perform(get("/api/users/{id}", id)).andReturn().getResponse().getStatus();
        // then
        assertEquals(expected, actual);
    }
}
