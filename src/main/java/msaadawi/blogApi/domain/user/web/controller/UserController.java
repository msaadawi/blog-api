package msaadawi.blogApi.domain.user.web.controller;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.controller.BaseController;
import msaadawi.blogApi.commons.resolver.PagingConfigResolver;
import msaadawi.blogApi.commons.resolver.SortingConfigsResolver;
import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.commons.validation.validator.PagingConfigValidator;
import msaadawi.blogApi.commons.validation.validator.SortingConfigsValidator;
import msaadawi.blogApi.domain.user.validator.UserRequestPayloadValidator;
import msaadawi.blogApi.domain.user.converter.DtoToUserConverter;
import msaadawi.blogApi.domain.user.converter.UserToDtoConverter;
import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.service.UserPersistenceService;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;
import msaadawi.blogApi.domain.user.web.payload.impl.DefaultRequestUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements BaseController {

    private final UserPersistenceService userPersistenceService;

    private final UserToDtoConverter userToDtoConverter;

    private final DtoToUserConverter dtoToUserConverter;

    private final UserRequestPayloadValidator reqPayloadValidator;

    private final PagingConfigResolver pagingConfigResolver;

    private final PagingConfigValidator pagingConfigValidator;

    private final SortingConfigsResolver sortingConfigsResolver;

    private final SortingConfigsValidator sortingConfigsValidator;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable("id") long id) {
        UserModel user = userPersistenceService.getById(id);
        return new ResponseEntity<>(userToDtoConverter.toUserDto(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody DefaultRequestUserDto reqUserDto) {
        reqPayloadValidator.validatePayloadForSingleCreate(reqUserDto);
        userPersistenceService.create(dtoToUserConverter.toUser(reqUserDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody DefaultRequestUserDto reqUserDto, @PathVariable("id") long id) {
        reqPayloadValidator.validatePayloadForSingleUpdate(reqUserDto);
        reqUserDto.setId(id);
        UserModel updateReadyUser = dtoToUserConverter.toUpdatableUser(reqUserDto);
        userPersistenceService.update(updateReadyUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable("id") long id) {
        userPersistenceService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<ResponseUserDto>> getAllUsers(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sort", required = false) String[] sort) {
        PagingConfiguration pagingConfig = pagingConfigResolver.doResolve(pageNumber, pageSize);
        pagingConfigValidator.doValidate(pagingConfig);
        List<SortingConfiguration> sortingConfigs = sortingConfigsResolver.doResolve(sort);
        sortingConfigsValidator.doValidate(sortingConfigs);
        PageResult<UserModel> userPageResult = userPersistenceService.fetchPage(pagingConfig, sortingConfigs);
        return new ResponseEntity<>(userToDtoConverter.toUserDtoPageResult(userPageResult), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateUsers(@RequestBody List<DefaultRequestUserDto> reqUserDtos) {
        reqPayloadValidator.validatePayloadForBulkUpdate(reqUserDtos);
        List<UserModel> updatableUsers = dtoToUserConverter.toUpdatableUserList(reqUserDtos);
        userPersistenceService.update(updatableUsers);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeAllUsers() {
        userPersistenceService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
