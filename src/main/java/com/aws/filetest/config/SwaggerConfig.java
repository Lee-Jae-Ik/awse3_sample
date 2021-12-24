package com.aws.filetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * SwaggerConfig
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aws.filetest"))
                .paths(PathSelectors.any())
                .build();
    }

    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "파일 테스트",
                "Api 요청할 때 지켜야 하는 URI 호출 규칙입니다.",
                "0.0.1-SNAPSHOT",
                "https://github.com/Lee-Jae-Ik",
                "OntactHealth",
                "License",
                "License URL"
        );
    }
}
