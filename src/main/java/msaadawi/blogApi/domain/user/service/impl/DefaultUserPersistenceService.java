package msaadawi.blogApi.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import msaadawi.blogApi.common.web.paging.PagingSettings;
import msaadawi.blogApi.common.web.paging.PagingConfigurer;
import msaadawi.blogApi.common.web.sorting.SortingSettings;
import msaadawi.blogApi.common.web.sorting.SortingConfigurer;
import msaadawi.blogApi.common.exception.EntityAlreadyExistException;
import msaadawi.blogApi.common.exception.EntityNotFoundException;
import msaadawi.blogApi.common.util.PageResult;
import msaadawi.blogApi.domain.user.converter.UserToEntityConverter;
import msaadawi.blogApi.domain.user.converter.EntityToUserConverter;
import msaadawi.blogApi.domain.user.data.entity.UserEntity;
import msaadawi.blogApi.domain.user.data.repository.UserRepository;
import msaadawi.blogApi.domain.user.model.UserModel;
import msaadawi.blogApi.domain.user.service.UserPersistenceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultUserPersistenceService implements UserPersistenceService {

    private final UserRepository userRepository;

    private final EntityToUserConverter entityToUserConverter;

    private final UserToEntityConverter userToEntityConverter;

    private final PagingConfigurer pagingConfigurer;

    private final SortingConfigurer sortingConfigurer;

    @Override
    public UserModel getById(Long id) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException("User with id " + id + " does not exist.");
        UserEntity userEntity = userRepository.getReferenceById(id);
        return entityToUserConverter.toUser(userEntity);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public UserModel create(UserModel userModel) {
        if (userModel == null) throw new IllegalArgumentException("userModel is null");
        if (userRepository.existsByUsername(userModel.getUsername()))
            throw new EntityAlreadyExistException("User with username " + userModel.getUsername()
                    + " already exists");
        if (userRepository.existsByEmail(userModel.getEmail()))
            throw new EntityAlreadyExistException("User with email " + userModel.getEmail()
                    + " already exists");
        UserEntity createdUserEntity = userRepository.save(userToEntityConverter.toUserEntity(userModel));
        return entityToUserConverter.toUser(createdUserEntity);
    }

    @Override
    @Transactional
    public UserModel update(UserModel userModel) {
        if (userModel == null)
            throw new IllegalArgumentException("userModel is null.");
        if (userModel.getId() == null)
            throw new IllegalArgumentException("Bad userModel parameter, id is null.");
        if (!userRepository.existsById(userModel.getId()))
            throw new EntityNotFoundException("User with id " + userModel.getId() + " does not exist.");
        UserEntity updatedUser = userRepository.save(userToEntityConverter.toUserEntity(userModel));
        return entityToUserConverter.toUser(updatedUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException("User with id " + id + " does not exist.");
        userRepository.deleteById(id);
    }

    @Override
    public PageResult<UserModel> fetchPage(PagingSettings pagingSettings, List<? extends SortingSettings> sortingSettingsList) {
        Pageable pageable = PageRequest.of(
                pagingConfigurer.configurePageNumber(pagingSettings),
                pagingConfigurer.configurePageSize(pagingSettings),
                sortingConfigurer.doConfigure(sortingSettingsList)
        );

        org.springframework.data.domain.Page<UserEntity> page = userRepository.findAll(pageable);

        PageResult<UserModel> ret = new PageResult<>();
        ret.setPageSize(page.getNumberOfElements());
        ret.setTotalSize(page.getTotalElements());
        List<UserModel> pageResultElements = entityToUserConverter.toUserList(page.getContent());
        ret.setElements(pageResultElements);
        ret.setLastPage(page.isLast());
        return ret;
    }

    @Override
    @Transactional
    public List<UserModel> update(List<UserModel> userModels) {
        if (userModels == null)
            throw new IllegalArgumentException("userModel list is null");
        for (UserModel user : userModels) {
            Long id = user.getId();
            if (id == null)
                throw new IllegalArgumentException("Encountered a user with a null id");
            if (!userRepository.existsById(id))
                throw new EntityNotFoundException("User with id " + id + " does not exist");
        }
        List<UserModel> ret = new ArrayList<>();
        for (UserModel user : userModels) {
            UserEntity updatedUserEntity = userRepository.save(userToEntityConverter.toUserEntity(user));
            ret.add(entityToUserConverter.toUser(updatedUserEntity));
        }
        return ret;
    }

    @Override
    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
