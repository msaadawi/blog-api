package msaadawi.blogApi.config.error;


import msaadawi.blogApi.common.error.DefaultApiErrorCustomizer;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfig {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultApiErrorCustomizer();
    }
}
