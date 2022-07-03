package msaadawi.blogApi.domain.comment.web.controller;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.comment.converter.CommentToDtoConverter;
import msaadawi.blogApi.domain.comment.converter.DtoToCommentConverter;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.service.CommentPersistenceService;
import msaadawi.blogApi.domain.comment.validation.validator.CommentRequestPayloadValidator;
import msaadawi.blogApi.domain.comment.web.payload.ResponseCommentDto;
import msaadawi.blogApi.domain.comment.web.payload.impl.DefaultRequestCommentDto;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.controller.BaseController;
import msaadawi.blogApi.commons.resolver.PagingConfigResolver;
import msaadawi.blogApi.commons.resolver.SortingConfigsResolver;
import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.commons.validation.validator.PagingConfigValidator;
import msaadawi.blogApi.commons.validation.validator.SortingConfigsValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController implements BaseController {

    private final CommentPersistenceService commentPersistenceService;

    private final CommentToDtoConverter commentToDtoConverter;

    private final DtoToCommentConverter dtoToCommentConverter;

    private final CommentRequestPayloadValidator reqPayloadValidator;

    private final PagingConfigResolver pagingConfigResolver;

    private final PagingConfigValidator pagingConfigValidator;

    private final SortingConfigsResolver sortingConfigsResolver;

    private final SortingConfigsValidator sortingConfigsValidator;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCommentDto> getComment(@PathVariable("id") long id) {
        CommentModel comment = commentPersistenceService.getById(id);
        return new ResponseEntity<>(commentToDtoConverter.toCommentDto(comment), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody DefaultRequestCommentDto reqCommentDto) {
        reqPayloadValidator.validatePayloadForSingleCreate(reqCommentDto);
        commentPersistenceService.create(dtoToCommentConverter.toComment(reqCommentDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@RequestBody DefaultRequestCommentDto reqCommentDto, @PathVariable("id") long id) {
        reqPayloadValidator.validatePayloadForSingleUpdate(reqCommentDto);
        reqCommentDto.setId(id);
        CommentModel updatableComment = dtoToCommentConverter.toUpdatableComment(reqCommentDto);
        commentPersistenceService.update(updatableComment);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeComment(@PathVariable("id") long id) {
        commentPersistenceService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<ResponseCommentDto>> getAllComments(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sort", required = false) String[] sort) {
        PagingConfiguration pagingConfig = pagingConfigResolver.doResolve(pageNumber, pageSize);
        pagingConfigValidator.doValidate(pagingConfig);
        List<SortingConfiguration> sortingConfigs = sortingConfigsResolver.doResolve(sort);
        sortingConfigsValidator.doValidate(sortingConfigs);
        PageResult<CommentModel> commentPageResult = commentPersistenceService.fetchPage(pagingConfig, sortingConfigs);
        return new ResponseEntity<>(commentToDtoConverter.toCommentDtoPageResult(commentPageResult), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateComments(@RequestBody List<DefaultRequestCommentDto> reqCommentDtos) {
        reqPayloadValidator.validatePayloadForBulkUpdate(reqCommentDtos);
        List<CommentModel> updatableComments = dtoToCommentConverter.toUpdatableCommentList(reqCommentDtos);
        commentPersistenceService.update(updatableComments);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeAllComments() {
        commentPersistenceService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
