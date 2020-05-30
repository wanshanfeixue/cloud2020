package com.edian.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {
    //配置了一个id为"com_edian"的路由规则，当访问地址：http://localhost:9527/guonei时会自动转发到
    //地址：https://news.baidu.com/guonei
    @Bean
    public RouteLocator consumerRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        //创建路由(多个)
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("com_edian",
                r -> r.path("/guonei")
                        .uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
