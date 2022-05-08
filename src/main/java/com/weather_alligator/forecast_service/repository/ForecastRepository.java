package com.weather_alligator.forecast_service.repository;

import com.weather_alligator.forecast_service.entity.ForecastEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ForecastRepository extends MongoRepository<ForecastEntity, String> {
}
