package io.github.gnobroga.mscloudgateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    
    // The configuration of gateway routes
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes() 
            .route(r -> 
                r.path("/clients/**")
                .uri("lb://msclients"))
            .route(r -> 
                r.path("/cards/**")
                .uri("lb://mscards"))
            .route(r ->
                 r.path("/card-evaluator/**")
                 .uri("lb://mscreditevaluator"))
        .build();
    }
}
