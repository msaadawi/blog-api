package msaadawi.blogApi.domain.post.data.repository;

import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "select p from PostEntity p where p.createdAt = p.lastUpdatedAt")
    List<PostEntity> findNonEditedPosts();
}
