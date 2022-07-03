package msaadawi.blogApi.domain.post.service;

import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.domain.post.model.PostModel;

import java.util.List;

public interface PostPersistenceService {

    PostModel getById(Long id);

    boolean existsById(Long id);

    PostModel create(PostModel postModel);

    PostModel update(PostModel postModel);

    void deleteById(Long id);

    PageResult<PostModel> fetchPage(PagingConfiguration pagingConfig, List<? extends SortingConfiguration> sortingConfigs);

    List<PostModel> update(List<PostModel> postModels);

    void deleteAll();
}
