package com.weather_alligator.forecast_service.transport.model;

import com.weather_alligator.forecast_service.enums.Duration;
import lombok.Data;

@Data
public class ForecastRequest {
    private Long userId;
    private Duration duration;
}
