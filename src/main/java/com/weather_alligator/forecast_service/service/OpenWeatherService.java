package com.weather_alligator.forecast_service.service;


import com.weather_alligator.forecast_service.enums.Duration;
import com.weather_alligator.forecast_service.enums.Source;
import com.weather_alligator.forecast_service.persistence.entity.ForecastEntity;
import com.weather_alligator.forecast_service.persistence.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;


@Component
public class OpenWeatherService {
    @Value("${openWeatherKey}")
    private String openWeatherKey;
    private final ForecastRepository repository;

    public OpenWeatherService(ForecastRepository repository) {
        this.repository = repository;
    }

    public void getForecast() {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&lang=ru&appid={appid}";
        RestTemplate restTemplate = new RestTemplate();
        var params = new HashMap<String, String>();
        params.put("lat", String.valueOf(53.23));
        params.put("lon", String.valueOf(23.4));
        params.put("appid", openWeatherKey);
        String weather;
        weather = restTemplate.exchange(url, HttpMethod.GET, null, String.class, params).getBody();
        ForecastEntity entity = new ForecastEntity(2L, Source.OPEN_WEATHER, Duration.CURRENT, Instant.now(), weather);
        repository.save(entity);
    }
}
