package com.weather_alligator.forecast_service.weatherApi;


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
public class OpenWeatherApi {
    @Value("${openWeatherKey}")
    private String openWeatherKey;
    private final ForecastRepository repository;

    public OpenWeatherApi(ForecastRepository repository) {
        this.repository = repository;
    }

    public void getForecast(Float lat, Float lon, Long userId) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&lang=ru&appid={appid}";
        RestTemplate restTemplate = new RestTemplate();
        var params = new HashMap<String, String>();
        params.put("lat", String.valueOf(lat));
        params.put("lon", String.valueOf(lon));
        params.put("appid", openWeatherKey);
        String weather;
        weather = restTemplate.exchange(url, HttpMethod.GET, null, String.class, params).getBody();
        ForecastEntity entity = new ForecastEntity(userId, Source.OPEN_WEATHER, Duration.CURRENT, Instant.now(), weather);
        repository.save(entity);
    }
}
