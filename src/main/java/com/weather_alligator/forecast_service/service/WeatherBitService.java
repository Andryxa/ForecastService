package com.weather_alligator.forecast_service.service;

import com.weather_alligator.forecast_service.enums.Duration;
import com.weather_alligator.forecast_service.enums.Source;
import com.weather_alligator.forecast_service.persistence.entity.ForecastEntity;
import com.weather_alligator.forecast_service.persistence.repository.ForecastRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class WeatherBitService {
    private final ForecastRepository repository;

    public WeatherBitService(ForecastRepository repository) {
        this.repository = repository;

    }

    @Value("${weatherBitKey}")
    private String weatherBitKey;

    public void getForecast() {
        String url = "https://api.weatherbit.io/v2.0/current?lat={lat}&lon={lon}&lang={lang}&key={key}";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("key", weatherBitKey);
        params.put("lat", String.valueOf(53.23));
        params.put("lon", String.valueOf(23.4));
        params.put("lang", "ru");

        String weather = restTemplate.exchange(url, HttpMethod.GET, null, String.class, params).getBody();
        ForecastEntity entity = new ForecastEntity(2L, Source.WEATHER_BIT, Duration.CURRENT, Instant.now(), weather);
        repository.save(entity);


    }
}
