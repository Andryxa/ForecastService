package com.weather_alligator.forecast_service.weatherApi;

import com.weather_alligator.forecast_service.enums.*;
import com.weather_alligator.forecast_service.persistence.entity.ForecastEntity;
import com.weather_alligator.forecast_service.persistence.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;

@Component
public class YandexApi {
    @Value("${yandexApiKey}")
    private String yandexKey;
    private final ForecastRepository repository;


    public YandexApi(ForecastRepository repository) {
        this.repository = repository;
    }

    public void getForecast(Float lat, Float lon, Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weather.yandex.ru/v1/fact?lat={lat}&lon={lon}&lang=ru_RU";

        var headers = new HttpHeaders();
        headers.set("X-Yandex-API-Key", yandexKey);
        var httpEntity = new HttpEntity<>(headers);

        var params = new HashMap<String, String>();
        params.put("lat", String.valueOf(lat));
        params.put("lon", String.valueOf(lon));

        String weather;
        weather = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, params).getBody();
        ForecastEntity entity = new ForecastEntity(userId, Source.YANDEX, Duration.CURRENT, Instant.now(), weather);
        repository.save(entity);
    }
}
