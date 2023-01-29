package msaadawi.blogApi.domain.comment.service;

import msaadawi.blogApi.domain.comment.model.CommentModel;
import msaadawi.blogApi.common.util.PageResult;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.web.sorting.SortingSettings;

import java.util.List;

public interface CommentPersistenceService {

    CommentModel getById(Long id);

    boolean existsById(Long id);

    CommentModel create(CommentModel commentModel);

    CommentModel update(CommentModel commentModel);

    void deleteById(Long id);

    PageResult<CommentModel> fetchPage(PagingSettings pagingSettings, List<? extends SortingSettings> sortingSettingsList);

    List<CommentModel> update(List<CommentModel> commentModels);

    void deleteAll();
}
