package msaadawi.blogApi.domain.post.service.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.paging.PagingConfigurer;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfigurer;
import msaadawi.blogApi.commons.exception.EntityNotFoundException;
import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.domain.post.converter.EntityToPostConverter;
import msaadawi.blogApi.domain.post.converter.PostToEntityConverter;
import msaadawi.blogApi.domain.post.data.repository.PostRepository;
import msaadawi.blogApi.domain.post.service.PostPersistenceService;
import msaadawi.blogApi.domain.user.service.UserPersistenceService;
import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import msaadawi.blogApi.domain.post.model.PostModel;
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
public class DefaultPostPersistenceService implements PostPersistenceService {

    private final PostRepository postRepository;

    private final EntityToPostConverter entityToPostConverter;

    private final PostToEntityConverter postToEntityConverter;

    private final UserPersistenceService userPersistenceService;

    private final PagingConfigurer pagingConfigurer;

    private final SortingConfigurer sortingConfigurer;

    @Override
    public PostModel getById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        if (!postRepository.existsById(id))
            throw new EntityNotFoundException("Post with id " + id + " does not exist");
        PostEntity postEntity = postRepository.getReferenceById(id);
        return entityToPostConverter.toPost(postEntity);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        return postRepository.existsById(id);
    }

    @Override
    @Transactional
    public PostModel create(PostModel postModel) {
        if (postModel == null)
            throw new IllegalArgumentException("postModel is null");
        if (postModel.getOwner() == null)
            throw new IllegalArgumentException("cannot create post with null owner");
        Long ownerId = postModel.getOwner().getId();
        if (ownerId == null)
            throw new IllegalArgumentException("Post's Owner id is null");
        if (!userPersistenceService.existsById(postModel.getOwner().getId()))
            throw new EntityNotFoundException("User with id " + ownerId + " does not exist");
        Date now = Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
        postModel.setCreatedAt(now);
        postModel.setLastUpdatedAt(now);
        PostEntity postEntityToCreate = postToEntityConverter.toPostEntity(postModel);
        PostEntity createdPostEntity = postRepository.save(postEntityToCreate);
        return entityToPostConverter.toPost(createdPostEntity);
    }

    @Override
    @Transactional
    public PostModel update(PostModel postModel) {
        if (postModel == null)
            throw new IllegalArgumentException("postModel is null.");
        if (postModel.getId() == null)
            throw new IllegalArgumentException("Bad postModel parameter, id is null.");
        if (!postRepository.existsById(postModel.getId()))
            throw new EntityNotFoundException("Post with id " + postModel.getId() + " does not exist.");
        PostEntity postEntityToUpdate = postToEntityConverter.toPostEntity(postModel);
        PostEntity updatedPostEntity = postRepository.save(postEntityToUpdate);
        return entityToPostConverter.toPost(updatedPostEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        if (!postRepository.existsById(id))
            throw new EntityNotFoundException("Post with id " + id + " does not exist.");
        postRepository.deleteById(id);
    }

    @Override
    public PageResult<PostModel> fetchPage(PagingConfiguration pagingConfig, List<? extends SortingConfiguration> sortingConfigs) {
        Pageable pageable = PageRequest.of(
                pagingConfigurer.configurePageNumber(pagingConfig),
                pagingConfigurer.configurePageSize(pagingConfig),
                sortingConfigurer.doConfigure(sortingConfigs)
        );

        org.springframework.data.domain.Page<PostEntity> page = postRepository.findAll(pageable);

        PageResult<PostModel> ret = new PageResult<>();
        ret.setPageSize(page.getNumberOfElements());
        ret.setTotalSize(page.getTotalElements());
        List<PostModel> pageResultElements = entityToPostConverter.toPostList(page.getContent());
        ret.setElements(pageResultElements);
        ret.setLastPage(page.isLast());
        return ret;
    }

    @Override
    @Transactional
    public List<PostModel> update(List<PostModel> postModels) {
        if (postModels == null)
            throw new IllegalArgumentException("postModel list is null");
        for (PostModel post : postModels) {
            Long id = post.getId();
            if (id == null)
                throw new IllegalArgumentException("encountered a post with a null id");
            if (!postRepository.existsById(id))
                throw new EntityNotFoundException("Post with id " + id + " does not exist");
        }
        List<PostModel> ret = new ArrayList<>();
        for (PostModel post : postModels) {
            PostEntity updatedPostEntity = postRepository.save(postToEntityConverter.toPostEntity(post));
            ret.add(entityToPostConverter.toPost(updatedPostEntity));
        }
        return ret;
    }

    @Override
    @Transactional
    public void deleteAll() {
        postRepository.deleteAll();
    }
}
