package msaadawi.blogApi.domain.user.service;

import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.util.PageResult;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.domain.user.model.UserModel;

import java.util.List;

public interface UserPersistenceService {

    UserModel getById(Long id);

    boolean existsById(Long id);

    UserModel create(UserModel userModel);

    UserModel update(UserModel userModel);

    void deleteById(Long id);

    PageResult<UserModel> fetchPage(PagingSettings pagingSettings, List<? extends SortingSettings> sortingSettingsList);

    List<UserModel> update(List<UserModel> userModels);

    void deleteAll();
}
