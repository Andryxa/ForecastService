package com.weather_alligator.forecast_service.transport.listener;

import com.weather_alligator.forecast_service.transport.model.ForecastRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ForecastListener {
    
    @RabbitListener(queues = "forecastQueue", messageConverter = "jsonConverter")
    public void getForecast(ForecastRequest request) {
        // TODO implement service call and delete this comment
    }
}
