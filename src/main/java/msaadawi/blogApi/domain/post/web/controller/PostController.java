package msaadawi.blogApi.domain.post.web.controller;


import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.controller.BaseController;
import msaadawi.blogApi.commons.resolver.PagingConfigResolver;
import msaadawi.blogApi.commons.resolver.SortingConfigsResolver;
import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.commons.validation.validator.PagingConfigValidator;
import msaadawi.blogApi.commons.validation.validator.SortingConfigsValidator;
import msaadawi.blogApi.domain.post.converter.DtoToPostConverter;
import msaadawi.blogApi.domain.post.converter.PostToDtoConverter;
import msaadawi.blogApi.domain.post.validation.validator.PostRequestPayloadValidator;
import msaadawi.blogApi.domain.post.web.payload.ResponsePostDto;
import msaadawi.blogApi.domain.post.web.payload.impl.DefaultRequestPostDto;
import msaadawi.blogApi.domain.post.model.PostModel;
import msaadawi.blogApi.domain.post.service.PostPersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController implements BaseController {

    private final PostPersistenceService postPersistenceService;

    private final PostToDtoConverter postToDtoConverter;

    private final DtoToPostConverter dtoToPostConverter;

    private final PostRequestPayloadValidator reqPayloadValidator;

    private final PagingConfigResolver pagingConfigResolver;

    private final PagingConfigValidator pagingConfigValidator;

    private final SortingConfigsResolver sortingConfigsResolver;

    private final SortingConfigsValidator sortingConfigsValidator;


    @GetMapping("/{id}")
    public ResponseEntity<ResponsePostDto> getPost(@PathVariable("id") long id) {
        PostModel post = postPersistenceService.getById(id);
        return new ResponseEntity<>(postToDtoConverter.toPostDto(post), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody DefaultRequestPostDto reqPostDto) {
        reqPayloadValidator.validatePayloadForSingleCreate(reqPostDto);
        postPersistenceService.create(dtoToPostConverter.toPost(reqPostDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@RequestBody DefaultRequestPostDto reqPostDto, @PathVariable("id") long id) {
        reqPayloadValidator.validatePayloadForSingleUpdate(reqPostDto);
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
        PagingConfiguration pagingConfig = pagingConfigResolver.doResolve(pageNumber, pageSize);
        pagingConfigValidator.doValidate(pagingConfig);
        List<SortingConfiguration> sortingConfigs = sortingConfigsResolver.doResolve(sort);
        sortingConfigsValidator.doValidate(sortingConfigs);
        PageResult<PostModel> postPageResult = postPersistenceService.fetchPage(pagingConfig, sortingConfigs);
        return new ResponseEntity<>(postToDtoConverter.toPostDtoPageResult(postPageResult), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updatePosts(@RequestBody List<DefaultRequestPostDto> reqPostDtos) {
        reqPayloadValidator.validatePayloadForBulkUpdate(reqPostDtos);
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
