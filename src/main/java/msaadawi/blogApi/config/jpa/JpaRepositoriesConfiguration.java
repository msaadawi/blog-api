package msaadawi.blogApi.config.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(enableDefaultTransactions = false, basePackages = "msaadawi.blogApi.domain")
public class JpaRepositoriesConfiguration {
}
