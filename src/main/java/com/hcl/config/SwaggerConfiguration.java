package com.hcl.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfiguration {

	  /**
	   * sets up swagger api
	   * @return bean
	   */
	  @Bean
	  public Docket api() {
	    List<ResponseMessage> responseMessages = Arrays.asList(
	      new ResponseMessageBuilder().code(401).message("TOKEN INVALID").build(),
	      new ResponseMessageBuilder().code(403).message("UNAUTHORIZED").build(),
	      new ResponseMessageBuilder().code(500).message("SYSTEM_ERROR").build());

	    return new Docket(DocumentationType.SWAGGER_2)
	      .apiInfo(apiInfo())
	      .select()
	      .paths(PathSelectors.any())
	      .build();
	  }
	  
	  /**
	   * Generates api info
	   *
	   * @return api info
	   */
	  private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	      .title("API Documentation")
	      .description("Documentation for api using Swagger UI")
	      .build();
	  }


}
