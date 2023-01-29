package msaadawi.blogApi.domain.post.service;

import msaadawi.blogApi.common.util.PageResult;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.domain.post.model.PostModel;

import java.util.List;

public interface PostPersistenceService {

    PostModel getById(Long id);

    boolean existsById(Long id);

    PostModel create(PostModel postModel);

    PostModel update(PostModel postModel);

    void deleteById(Long id);

    PageResult<PostModel> fetchPage(PagingSettings pagingSettings, List<? extends SortingSettings> sortingSettingsList);

    List<PostModel> update(List<PostModel> postModels);

    void deleteAll();
}
