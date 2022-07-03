package msaadawi.blogApi.domain.comment.service;

import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;

import java.util.List;

public interface CommentPersistenceService {

    CommentModel getById(Long id);

    boolean existsById(Long id);

    CommentModel create(CommentModel commentModel);

    CommentModel update(CommentModel commentModel);

    void deleteById(Long id);

    PageResult<CommentModel> fetchPage(PagingConfiguration pagingConfig, List<? extends SortingConfiguration> sortingConfigs);

    List<CommentModel> update(List<CommentModel> commentModels);

    void deleteAll();
}
