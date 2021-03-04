package com.compasso.productms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${info.app.description}")
    String description;

    @Value("${info.app.name}")
    String name;

    @Value("${info.app.version}")
    String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.compasso.productms.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.POST, mensagensRespostaGet());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .version(version)
                .contact(new Contact("Danniel Dias Braga", "", "dannieldiasbraga@gmail.com"))
                .build();
    }

    private List<ResponseMessage> mensagensRespostaGet() {
        List<ResponseMessage> listaErros = new ArrayList<>();
        listaErros.add(new ResponseMessageBuilder()
                .code(500)
                .message("Algo inesperado ocorreu no servidor.")
                .build());
        return listaErros;
    }
}
