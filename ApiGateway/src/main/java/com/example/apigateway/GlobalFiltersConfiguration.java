package com.example.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

    final Logger logger = LoggerFactory.getLogger(MyPreFilter.class);

    @Bean
    @Order(1)
    public GlobalFilter secondFilter(){
        return ((exchange, chain) -> {
            logger.info("Second Global Pre-Filter executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("third Global Post-Filter executed...");
            }));
        });
    }


    @Bean
    @Order(2)
    public GlobalFilter thirdFilter(){
        return ((exchange, chain) -> {
            logger.info("Third Global Pre-Filter executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Second Global Post-Filter executed...");
            }));
        });
    }

    @Bean
    @Order(3)
    public GlobalFilter fourthFilter(){
        return ((exchange, chain) -> {
            logger.info("Fourth Global Pre-Filter executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("First Global Post-Filter executed...");
            }));
        });
    }

}
