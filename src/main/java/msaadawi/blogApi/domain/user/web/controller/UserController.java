package msaadawi.blogApi.domain.user.web.controller;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.common.util.PageResult;
import msaadawi.blogApi.common.validation.validator.PagingSettingsValidator;
import msaadawi.blogApi.common.validation.validator.SortingSettingsValidator;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.web.paging.PagingSettingsResolver;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.web.sorting.SortingSettingsResolver;
import msaadawi.blogApi.domain.user.converter.DtoToUserConverter;
import msaadawi.blogApi.domain.user.converter.UserToDtoConverter;
import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.service.UserPersistenceService;
import msaadawi.blogApi.domain.user.validator.UserRequestPayloadValidator;
import msaadawi.blogApi.domain.user.web.payload.RequestUserDto;
import msaadawi.blogApi.domain.user.web.payload.ResponseUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserPersistenceService userPersistenceService;

    private final UserToDtoConverter userToDtoConverter;

    private final DtoToUserConverter dtoToUserConverter;

    private final UserRequestPayloadValidator reqPayloadValidator;

    private final PagingSettingsResolver pagingSettingsResolver;

    private final PagingSettingsValidator pagingSettingsValidator;

    private final SortingSettingsResolver sortingSettingsResolver;

    private final SortingSettingsValidator sortingSettingsValidator;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable("id") long id) {
        UserModel user = userPersistenceService.getById(id);
        return new ResponseEntity<>(userToDtoConverter.toUserDto(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody RequestUserDto reqUserDto) {
        reqPayloadValidator.validatePayloadForSingleCreateOp(reqUserDto);
        userPersistenceService.create(dtoToUserConverter.toUser(reqUserDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody RequestUserDto reqUserDto, @PathVariable("id") long id) {
        reqPayloadValidator.validatePayloadForSingleUpdateOp(reqUserDto);
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
        PagingSettings pagingSettings = pagingSettingsResolver.doResolve(pageNumber, pageSize);
        pagingSettingsValidator.doValidate(pagingSettings);
        List<SortingSettings> sortingSettingsList = sortingSettingsResolver.doResolve(sort);
        sortingSettingsValidator.doValidate(sortingSettingsList);
        PageResult<UserModel> userPageResult = userPersistenceService.fetchPage(pagingSettings, sortingSettingsList);
        return new ResponseEntity<>(userToDtoConverter.toUserDtoPageResult(userPageResult), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateUsers(@RequestBody List<RequestUserDto> reqUserDtos) {
        reqPayloadValidator.validatePayloadForBulkUpdateOp(reqUserDtos);
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
