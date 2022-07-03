package msaadawi.blogApi.domain.post.data.repository;

import msaadawi.blogApi.domain.post.data.entity.PostEntity;
import msaadawi.blogApi.domain.post.data.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class PostRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:14-alpine"));

    @DynamicPropertySource
    static void overrideDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private PostRepository underTest;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void should_ReturnPostsWithSameCreatedAtAndLastUpdatedAtValues_For_FindNonEditedPosts() {
        // given
        Instant instant = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Date c1 = Date.from(instant);
        Date l1 = Date.from(instant);

        Date c2 = Date.from(instant);
        Date l2 = Date.from(instant.plusSeconds(10)); // just some different date-time value
        PostEntity p1 = PostEntity.builder()
                .createdAt(c1)
                .lastUpdatedAt(l1)
                .build();
        PostEntity p2 = PostEntity.builder()
                .createdAt(c2)
                .lastUpdatedAt(l2)
                .build();
        p1 = underTest.save(p1);
        p2 = underTest.save(p2);
        List<PostEntity> expected = Collections.singletonList(p1);
        // when
        List<PostEntity> actual = underTest.findNonEditedPosts();
        // then
        assertIterableEquals(expected, actual);
    }
}
