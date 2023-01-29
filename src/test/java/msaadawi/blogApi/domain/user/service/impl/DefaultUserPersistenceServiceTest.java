package msaadawi.blogApi.domain.user.service.impl;

import msaadawi.blogApi.common.web.paging.PagingConfigurer;
import msaadawi.blogApi.common.web.sorting.SortingConfigurer;
import msaadawi.blogApi.common.exception.EntityNotFoundException;
import msaadawi.blogApi.domain.user.converter.EntityToUserConverter;
import msaadawi.blogApi.domain.user.converter.UserToEntityConverter;
import msaadawi.blogApi.domain.user.data.entity.UserEntity;
import msaadawi.blogApi.domain.user.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DefaultUserPersistenceServiceTest {

    private DefaultUserPersistenceService underTest;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private EntityToUserConverter entityToUserConverterMock;

    @Mock
    private UserToEntityConverter userToEntityConverterMock;

    @Mock
    private PagingConfigurer pagingConfigurerMock;

    @Mock
    private SortingConfigurer sortingConfigurerMock;

    @BeforeEach
    void setUp() {
        underTest = new DefaultUserPersistenceService(userRepositoryMock, entityToUserConverterMock,
                userToEntityConverterMock, pagingConfigurerMock, sortingConfigurerMock);
    }

    @Test
    void should_throwIllegalArgumentException_For_GetById_When_IdIsNull() {
        // given
        Long inputId = null;
        // when
        Executable executable = () -> underTest.getById(inputId);
        // then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void should_throwEntityNotFoundException_For_GetById_When_IdDoesNotExist() {
        // given
        Long nonExistingUserIdInput = 0L;
        given(userRepositoryMock.existsById(nonExistingUserIdInput)).willReturn(false);
        // when
        Executable executable = () -> underTest.getById(nonExistingUserIdInput);
        // then
        assertThrows(EntityNotFoundException.class, executable);
    }

    @Test
    void should_InvokeGetReferenceById_In_UserRepository_For_GetById() {
        // given
        Long inputId = 1L;
        given(userRepositoryMock.existsById(inputId)).willReturn(true);
        // when
        underTest.getById(inputId);
        // then
        then(userRepositoryMock).should(times(1)).getReferenceById(inputId);
    }

    @Test
    void should_InvokeToUser_In_EntityToUserConverter_For_GetById() {
        // given
        Long inputId = 1L;
        given(userRepositoryMock.existsById(inputId)).willReturn(true);
        UserEntity expectedUserEntity = UserEntity.builder().id(inputId).build();
        given(userRepositoryMock.getReferenceById(inputId)).willReturn(expectedUserEntity);
        // when
        underTest.getById(inputId);
        // then
        then(entityToUserConverterMock).should(times(1)).toUser(expectedUserEntity);
    }

    @Test
    void should_throwIllegalArgumentException_For_ExistsById_When_IdIsNull() {
        // given
        Long inputId = null;
        // when
        Executable executable = () -> underTest.existsById(inputId);
        // then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void should_ReturnExpectedValue_For_ExistsById(boolean userExists) {
        // given
        given(userRepositoryMock.existsById(any())).willReturn(userExists);
        boolean expected = userExists;
        // when
        boolean actual = underTest.existsById(anyLong());
        // then
        assertEquals(expected, actual);
    }
}