package msaadawi.blogApi.domain.comment.service.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.domain.comment.converter.CommentToEntityConverter;
import msaadawi.blogApi.domain.comment.converter.EntityToCommentConverter;
import msaadawi.blogApi.domain.comment.data.entity.CommentEntity;
import msaadawi.blogApi.domain.comment.data.repository.CommentRepository;
import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.domain.comment.service.CommentPersistenceService;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.paging.PagingConfigurer;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfigurer;
import msaadawi.blogApi.commons.exception.EntityNotFoundException;
import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.domain.post.service.PostPersistenceService;
import msaadawi.blogApi.domain.user.service.UserPersistenceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultCommentPersistenceService implements CommentPersistenceService {

    private final CommentRepository commentRepository;

    private final PostPersistenceService postPersistenceService;

    private final UserPersistenceService userPersistenceService;

    private final EntityToCommentConverter entityToCommentConverter;

    private final CommentToEntityConverter commentToEntityConverter;

    private final PagingConfigurer pagingConfigurer;

    private final SortingConfigurer sortingConfigurer;

    @Override
    public CommentModel getById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        if (!commentRepository.existsById(id))
            throw new EntityNotFoundException("Comment with id " + id + " does not exist");
        CommentEntity commentEntity = commentRepository.getReferenceById(id);
        return entityToCommentConverter.toComment(commentEntity);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        return commentRepository.existsById(id);
    }

    @Override
    @Transactional
    public CommentModel create(CommentModel commentModel) {
        if (commentModel == null)
            throw new IllegalArgumentException("commentModel is null");
        verifyCommentPostBeforeCreate(commentModel);
        verifyCommentOwnerBeforeCreate(commentModel);

        Date now = Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        commentModel.setCreatedAt(now);
        commentModel.setLastUpdatedAt(now);

        CommentEntity commentEntityToCreate = commentToEntityConverter.toCommentEntity(commentModel);
        CommentEntity createdCommentEntity = commentRepository.save(commentEntityToCreate);
        return entityToCommentConverter.toComment(createdCommentEntity);
    }

    @Override
    @Transactional
    public CommentModel update(CommentModel commentModel) {
        verifyCommentBeforeUpdate(commentModel);
        CommentEntity commentEntityToUpdate = commentToEntityConverter.toCommentEntity(commentModel);
        CommentEntity updatedCommentEntity = commentRepository.save(commentEntityToUpdate);
        return entityToCommentConverter.toComment(updatedCommentEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        verifyCommentBeforeDelete(id);
        commentRepository.deleteById(id);
    }

    @Override
    public PageResult<CommentModel> fetchPage(PagingConfiguration pagingConfig, List<? extends SortingConfiguration> sortingConfigs) {
        Pageable pageable = PageRequest.of(
                pagingConfigurer.configurePageNumber(pagingConfig),
                pagingConfigurer.configurePageSize(pagingConfig),
                sortingConfigurer.doConfigure(sortingConfigs)
        );

        org.springframework.data.domain.Page<CommentEntity> page = commentRepository.findAll(pageable);

        PageResult<CommentModel> ret = new PageResult<>();
        ret.setPageSize(page.getNumberOfElements());
        ret.setTotalSize(page.getTotalElements());
        List<CommentModel> pageResultElements = entityToCommentConverter.toCommentList(page.getContent());
        ret.setElements(pageResultElements);
        ret.setLastPage(page.isLast());
        return ret;
    }

    @Override
    @Transactional
    public List<CommentModel> update(List<CommentModel> commentModels) {
        if (commentModels == null)
            throw new IllegalArgumentException("commentModel list is null");
        for (CommentModel comment : commentModels) {
            Long id = comment.getId();
            if (id == null)
                throw new IllegalArgumentException("Encountered a comment with a null id");
            if (!commentRepository.existsById(id))
                throw new EntityNotFoundException("Comment with id " + id + " does not exist");
        }
        List<CommentModel> ret = new ArrayList<>();
        for (CommentModel comment : commentModels) {
            CommentEntity updatedCommentEntity = commentRepository
                    .save(commentToEntityConverter.toCommentEntity(comment));
            ret.add(entityToCommentConverter.toComment(updatedCommentEntity));
        }
        return ret;
    }

    @Override
    @Transactional
    public void deleteAll() {
        commentRepository.deleteAll();
    }

    private void verifyCommentPostBeforeCreate(CommentModel comment) {
        if (comment.getPostAddedTo() == null)
            throw new IllegalArgumentException("Comment cannot be assigned to a null post");
        Long postId = comment.getPostAddedTo().getId();
        if (postId == null)
            throw new IllegalArgumentException("Post the comment is to be assigned to has null id");
        if (!postPersistenceService.existsById(postId))
            throw new EntityNotFoundException("Post with id " + postId + " does not exist");
    }

    private void verifyCommentOwnerBeforeCreate(CommentModel comment) {
        if (comment.getOwner() == null)
            throw new IllegalArgumentException("cannot create comment with null owner");
        Long ownerId = comment.getOwner().getId();
        if (ownerId == null)
            throw new IllegalArgumentException("Comment's owner id is null");
        if (!userPersistenceService.existsById(ownerId))
            throw new EntityNotFoundException("User with id " + ownerId + " does not exist");
    }

    private void verifyCommentBeforeUpdate(CommentModel comment) {
        if (comment == null)
            throw new IllegalArgumentException("comment is null.");
        Long commentId = comment.getId();
        if (commentId == null)
            throw new IllegalArgumentException("Comment's id is null.");
        if (!commentRepository.existsById(commentId))
            throw new EntityNotFoundException("Comment with id " + commentId + " does not exist.");
    }

    private void verifyCommentBeforeDelete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        if (!commentRepository.existsById(id))
            throw new EntityNotFoundException("Comment with id " + id + " does not exist.");
    }
}
