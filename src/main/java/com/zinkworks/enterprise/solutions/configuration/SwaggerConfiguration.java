package com.zinkworks.enterprise.solutions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {
	
	
	  @Bean 
	  public Docket productApi() { 
		  return new
				  Docket(DocumentationType.SWAGGER_2) 
				  .select() 
				  .apis(RequestHandlerSelectors
						  .basePackage("com.zinkworks.enterprise.solutions.controller")) 
				  .build(); 
		  }
	 

}
