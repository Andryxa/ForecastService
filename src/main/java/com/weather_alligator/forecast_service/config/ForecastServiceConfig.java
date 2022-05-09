package com.weather_alligator.forecast_service.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ForecastServiceConfig {

    @Bean
    public DirectExchange forecastServiceExchange() {
        return new DirectExchange("forecastServiceExchange");
    }
    
    @Bean("jsonConverter")
    public Jackson2JsonMessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
