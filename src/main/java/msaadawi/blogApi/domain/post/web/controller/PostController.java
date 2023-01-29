package msaadawi.blogApi.domain.post.web.controller;


import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.common.util.PageResult;
import msaadawi.blogApi.common.validation.validator.PagingSettingsValidator;
import msaadawi.blogApi.common.validation.validator.SortingSettingsValidator;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.web.paging.PagingSettingsResolver;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.web.sorting.SortingSettingsResolver;
import msaadawi.blogApi.domain.post.converter.DtoToPostConverter;
import msaadawi.blogApi.domain.post.converter.PostToDtoConverter;
import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.post.service.PostPersistenceService;
import msaadawi.blogApi.domain.post.validation.validator.PostRequestPayloadValidator;
import msaadawi.blogApi.domain.post.web.payload.RequestPostDto;
import msaadawi.blogApi.domain.post.web.payload.ResponsePostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostPersistenceService postPersistenceService;

    private final PostToDtoConverter postToDtoConverter;

    private final DtoToPostConverter dtoToPostConverter;

    private final PostRequestPayloadValidator reqPayloadValidator;

    private final PagingSettingsResolver pagingSettingsResolver;

    private final PagingSettingsValidator pagingSettingsValidator;

    private final SortingSettingsResolver sortingSettingsResolver;

    private final SortingSettingsValidator sortingSettingsValidator;


    @GetMapping("/{id}")
    public ResponseEntity<ResponsePostDto> getPost(@PathVariable("id") long id) {
        PostModel post = postPersistenceService.getById(id);
        return new ResponseEntity<>(postToDtoConverter.toPostDto(post), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody RequestPostDto reqPostDto) {
        reqPayloadValidator.validatePayloadForSingleCreateOp(reqPostDto);
        postPersistenceService.create(dtoToPostConverter.toPost(reqPostDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@RequestBody RequestPostDto reqPostDto, @PathVariable("id") long id) {
        reqPayloadValidator.validatePayloadForSingleUpdateOp(reqPostDto);
        reqPostDto.setId(id);
        PostModel updatablePost = dtoToPostConverter.toUpdatablePost(reqPostDto);
        postPersistenceService.update(updatablePost);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePost(@PathVariable("id") long id) {
        postPersistenceService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<ResponsePostDto>> getAllPosts(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sort", required = false) String[] sort) {
        PagingSettings pagingSettings = pagingSettingsResolver.doResolve(pageNumber, pageSize);
        pagingSettingsValidator.doValidate(pagingSettings);
        List<SortingSettings> sortingSettingsList = sortingSettingsResolver.doResolve(sort);
        sortingSettingsValidator.doValidate(sortingSettingsList);
        PageResult<PostModel> postPageResult = postPersistenceService.fetchPage(pagingSettings, sortingSettingsList);
        return new ResponseEntity<>(postToDtoConverter.toPostDtoPageResult(postPageResult), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updatePosts(@RequestBody List<RequestPostDto> reqPostDtos) {
        reqPayloadValidator.validatePayloadForBulkUpdateOp(reqPostDtos);
        List<PostModel> updatablePosts = dtoToPostConverter.toUpdatablePostList(reqPostDtos);
        postPersistenceService.update(updatablePosts);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeAllPosts() {
        postPersistenceService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
