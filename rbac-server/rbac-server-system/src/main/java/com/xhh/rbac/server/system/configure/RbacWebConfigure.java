package com.xhh.rbac.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.xhh.rbac.server.system.properties.RbacServerSystemProperties;
import com.xhh.rbac.server.system.properties.RbacSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*
*
securitySchemes：用于配置安全策略，比如配置认证模型，scope等内容；
securityContexts：用于配置安全上下文，只有配置了安全上下文的接口才能使用令牌获取资源。
* */
@Configuration
@EnableSwagger2// 表示开启Swagger功能
public class RbacWebConfigure {

    @Autowired
    private RbacServerSystemProperties properties;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    /*
    swaggerApi方法的apis(RequestHandlerSelectors.basePackage("com.xhh.rbac.server.system.controller"))表示将com.xhh.rbac.server.system.controller路径下的所有Controller都添加进去
    * */
    @Bean
    public Docket swaggerApi() {
        RbacSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger))
                .securitySchemes(Collections.singletonList(securityScheme(swagger)))
                .securityContexts(Collections.singletonList(securityContext(swagger)));
    }

    /*apiInfo用于定义一些API页面信息，比如作者名称，邮箱，网站链接，开源协议等等。*/
    private ApiInfo apiInfo(RbacSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

    /*
    方法设置了安全策略和安全上下文。

在securityScheme方法中，我们通过OAuthBuilder对象构建了安全策略，主要配置了认证类型为ResourceOwnerPasswordCredentialsGrant（即密码模式），认证地址为http://localhost:8301/auth/oauth/token（即通过网关转发到认证服务器），scope为test，
    * */
    private SecurityScheme securityScheme(RbacSwaggerProperties swagger) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());

        return new OAuthBuilder()
                .name(swagger.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    private SecurityContext securityContext(RbacSwaggerProperties swagger) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(RbacSwaggerProperties swagger) {
        return new AuthorizationScope[]{
                new AuthorizationScope(swagger.getScope(), "")
        };
    }
}