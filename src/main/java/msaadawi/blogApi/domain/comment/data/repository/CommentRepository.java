package msaadawi.blogApi.domain.comment.data.repository;

import msaadawi.blogApi.domain.comment.data.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
