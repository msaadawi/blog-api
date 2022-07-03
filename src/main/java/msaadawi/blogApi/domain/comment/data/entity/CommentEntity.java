package msaadawi.blogApi.domain.comment.data.entity;

import lombok.*;
import msaadawi.blogApi.commons.entity.BaseEntity;
import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import msaadawi.blogApi.domain.user.data.entity.UserEntity;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "comment")
public class CommentEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "last_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private PostEntity postAddedTo;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity owner;
}
