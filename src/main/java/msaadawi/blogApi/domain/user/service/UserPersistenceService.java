package msaadawi.blogApi.domain.user.service;

import msaadawi.blogApi.commons.config.paging.PagingConfiguration;
import msaadawi.blogApi.commons.util.PageResult;
import msaadawi.blogApi.commons.config.sorting.SortingConfiguration;
import msaadawi.blogApi.domain.user.model.UserModel;

import java.util.List;

public interface UserPersistenceService {

    UserModel getById(Long id);

    boolean existsById(Long id);

    UserModel create(UserModel userModel);

    UserModel update(UserModel userModel);

    void deleteById(Long id);

    PageResult<UserModel> fetchPage(PagingConfiguration pagingConfig, List<? extends SortingConfiguration> sortingConfigs);

    List<UserModel> update(List<UserModel> userModels);

    void deleteAll();
}
