package todo1.hulkstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class HulkstoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(HulkstoreApplication.class, args);
  }

  @Bean
  public Docket swaggerConfig() {
    return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
        .apis(RequestHandlerSelectors.basePackage("todo1.hulkstore.controller")).build();

  }
}
