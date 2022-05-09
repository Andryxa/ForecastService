package com.weather_alligator.forecast_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ForecastConfig {
    
    @Bean("forecastQueue")
    public Queue forecastQueue() {
        return new Queue("forecastQueue");
    }

    @Bean
    public Binding forecastBinding(Queue forecastQueue, DirectExchange exchange) {
        return BindingBuilder.bind(forecastQueue).to(exchange).with("forecast_service.forecast");
    }
}
