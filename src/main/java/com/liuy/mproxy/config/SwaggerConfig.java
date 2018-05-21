package com.liuy.mproxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Title: SwaggerConfig. </p>
 * <p>Swagger api 接口文档设置 </p>
 * <p>Company: http://www.hnxianyi.com </p>
 * @author liuy
 * @date 2018/1/21 13:31
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDoc() {
		return docket();
	}

	private Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
		        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
		        .build().ignoredParameterTypes(Errors.class).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact=new Contact("交易中心",
				"http://www.hnxianyi.com","pdj@hnxianyi.com");
		return new ApiInfoBuilder()
				.title("交易中心接口文档")
				.description("交易中心接口文档API")
				.contact(contact)
				.version("1.0")
				.build();
	}
}