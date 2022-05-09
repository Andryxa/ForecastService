package com.weather_alligator.forecast_service.transport.listener;

import com.weather_alligator.forecast_service.transport.model.ForecastRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForecastListener {

    public static final String USER_SERVICE_LOCATION_RK = "user_service.location";
    public static final String USER_SERVICE_EXCHANGE = "userServiceExchange";

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "forecastQueue", messageConverter = "jsonConverter")
    public void getForecast(ForecastRequest request) {
        log.info("Received forecast request: {}", request);

        var locationRequest = new LocationRequest(request.getUserId());
        log.info("Sending location request: {}", locationRequest);
        var locationResponse = (LocationResponse) rabbitTemplate.convertSendAndReceiveAsType(USER_SERVICE_EXCHANGE, USER_SERVICE_LOCATION_RK, locationRequest, ParameterizedTypeReference.forType(LocationResponse.class));
        log.info("Received location response: {}", locationResponse);
        // TODO add handling of request and send response
    }

    record LocationRequest(Long userId) {}
    record LocationResponse(Float lat, Float lon) {}
}
