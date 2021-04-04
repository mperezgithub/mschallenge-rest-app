package com.intercorpretail.mschallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {
	@Value("${swagger.title}")
	private String appTitle;

	@Bean
	public Docket produceApi() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).select()
				.apis(RequestHandlerSelectors.basePackage("com.intercorpretail.mschallenge.controller"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				appTitle,
				"A challenge application based on java and spring boot",
				"v1",
				"Terms of service",
				"amarioperez@gmail.com",
				"License of API",
				"https://swagger.io/docs/");
		return apiInfo;
	}
}