package msaadawi.blogApi.domain.comment.web.controller;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.common.util.PageResult;
import msaadawi.blogApi.common.validation.validator.PagingSettingsValidator;
import msaadawi.blogApi.common.validation.validator.SortingSettingsValidator;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.web.paging.PagingSettingsResolver;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.web.sorting.SortingSettingsResolver;
import msaadawi.blogApi.domain.comment.converter.CommentToDtoConverter;
import msaadawi.blogApi.domain.comment.converter.DtoToCommentConverter;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.service.CommentPersistenceService;
import msaadawi.blogApi.domain.comment.validation.validator.CommentRequestPayloadValidator;
import msaadawi.blogApi.domain.comment.web.payload.RequestCommentDto;
import msaadawi.blogApi.domain.comment.web.payload.ResponseCommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentPersistenceService commentPersistenceService;

    private final CommentToDtoConverter commentToDtoConverter;

    private final DtoToCommentConverter dtoToCommentConverter;

    private final CommentRequestPayloadValidator reqPayloadValidator;

    private final PagingSettingsResolver pagingSettingsResolver;

    private final PagingSettingsValidator pagingSettingsValidator;

    private final SortingSettingsResolver sortingSettingsResolver;

    private final SortingSettingsValidator sortingSettingsValidator;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCommentDto> getComment(@PathVariable("id") long id) {
        CommentModel comment = commentPersistenceService.getById(id);
        return new ResponseEntity<>(commentToDtoConverter.toCommentDto(comment), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody RequestCommentDto reqCommentDto) {
        reqPayloadValidator.validatePayloadForSingleCreateOp(reqCommentDto);
        commentPersistenceService.create(dtoToCommentConverter.toComment(reqCommentDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@RequestBody RequestCommentDto reqCommentDto, @PathVariable("id") long id) {
        reqPayloadValidator.validatePayloadForSingleUpdateOp(reqCommentDto);
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
        PagingSettings pagingSettings = pagingSettingsResolver.doResolve(pageNumber, pageSize);
        pagingSettingsValidator.doValidate(pagingSettings);
        List<SortingSettings> sortingSettingsList = sortingSettingsResolver.doResolve(sort);
        sortingSettingsValidator.doValidate(sortingSettingsList);
        PageResult<CommentModel> commentPageResult = commentPersistenceService.fetchPage(pagingSettings, sortingSettingsList);
        return new ResponseEntity<>(commentToDtoConverter.toCommentDtoPageResult(commentPageResult), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateComments(@RequestBody List<RequestCommentDto> reqCommentDtos) {
        reqPayloadValidator.validatePayloadForBulkUpdateOp(reqCommentDtos);
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
