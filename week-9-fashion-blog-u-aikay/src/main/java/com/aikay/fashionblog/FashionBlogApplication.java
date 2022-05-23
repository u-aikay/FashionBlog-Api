package com.aikay.fashionblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
//@EnableWebMvc
public class FashionBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FashionBlogApplication.class, args);
    }

//    @Bean
//    public Docket swaggerConfig(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select().paths(PathSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.aikay"))
//                .build();
//    }
}
